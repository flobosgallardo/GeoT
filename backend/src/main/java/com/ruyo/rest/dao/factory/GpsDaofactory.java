package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.CelularDao;
import com.ruyo.rest.dao.CoordenadaDao;
import com.ruyo.rest.dao.GpsDao;
import com.ruyo.rest.dao.VehiculoDao;
import com.ruyo.rest.dao.repository.GpsRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Celular;
import com.ruyo.rest.entity.Coordenada;
import com.ruyo.rest.entity.Gps;
import com.ruyo.rest.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("gpsDao") @SuppressWarnings("unchecked")
public class GpsDaofactory extends FactoryUtility implements GpsDao {

    private GpsRepository gpsRepository;
    private CelularDao celularDaoFactory;
    private VehiculoDao vehiculoDaoFactory;
    private CoordenadaDao coordenadaDaoFactory;

    @Autowired
    public GpsDaofactory(@Qualifier("gpsRepository") GpsRepository gpsRepository,
                         @Qualifier("celularDao") CelularDao celularDaoFactory,
                         @Qualifier("vehiculoDao") VehiculoDao vehiculoDaoFactory,
                         CoordenadaDao coordenadaDaoFactory){
        this.gpsRepository = gpsRepository;
        this.celularDaoFactory = celularDaoFactory;
        this.vehiculoDaoFactory = vehiculoDaoFactory;
        this.coordenadaDaoFactory = coordenadaDaoFactory;
    }

    @Override
    public List<Gps> getListGps() {
        return gpsRepository.findAll();
    }

    @Override
    public Optional<Gps> getGps(Long gps_id) {
        return gpsRepository.findById(gps_id);
    }

    @Override
    public Gps getOneById(Long gps_id){
        return gpsRepository.getOne(gps_id);
    }

    @Override
    public List getCoordByGps(Long gps_id) {

       try {
            Gps gps = getOneById(gps_id);

            if(gps != null) {
                double lat_new;
                double long_new;
                double lat_old = 0.0;
                double long_old = 0.0;
                double distance = 0;
                List  myList = new ArrayList<>();
                List<Coordenada> coordenadas = gps.getCoordenadas();
                if (coordenadas != null) {
                    for (Coordenada c : coordenadas) {
                        lat_new = c.getCoordenadaX();
                        long_new = c.getCoordenadaY();
                        if (lat_new != 0 && long_new != 0) {
                            if (long_old != 0.0 && lat_old != 0.0) {
                                if (c.getDistancia() == 0) {
                                    distance = getDistance(lat_new, long_new, lat_old, long_old);
                                    c.setDistancia(distance);
                                    coordenadaDaoFactory.update(c);
                                } else {
                                    distance = getDistance(lat_new, long_new, lat_old, long_old);
                                }
                            }
                        }
                        myList.add(lat_new+ "," + long_new + "," +
                                distance);
                        lat_old = lat_new;
                        long_old = long_new;
                    }
                }
                return myList;
            }

        }catch (Exception ex){
            System.err.println(ex.toString());
           return null;
        }
        return null;
    }



    @Override
    public Gps addGps(Gps gps, Long vehiculo_id, Long celular_id) {
        gps.setHabilitado(true);
        gps.setBorrado(false);
        if(vehiculo_id != null) {
            Vehiculo vehiculo = vehiculoDaoFactory.getOneById(vehiculo_id);
            gps.setVehiculo(vehiculo);
        }else if(celular_id != null){
            Celular celular = celularDaoFactory.getOneById(celular_id);
            gps.setCelular(celular);
            celular.setGps(gps);
        }
        return (Gps) addEntity(gps);
    }

    @Override
    public boolean delete(Long gps_id) {
        Gps gps = gpsRepository.getOne(gps_id);
        gps.setBorrado(false);
        return deleteEntity(gps);
    }

    @Override
    public Gps update(Long gps_id, Gps gps) {
        gps.setGpsId(gps_id);
        return (Gps) updateEntity(gps);
    }

}
