package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.VehiculoDao;
import com.ruyo.rest.dao.repository.VehiculoRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository("vehiculoDao") @SuppressWarnings( "unchecked" )
public class VehiculoDaoFactory extends FactoryUtility implements VehiculoDao {


    private VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoDaoFactory(@Qualifier("vehiculoRepository") VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> getVehiculo(Long vehiculo_id) {
        return vehiculoRepository.findById(vehiculo_id);
    }

    @Override
    public Vehiculo getOneById(Long vehiculo_id) {
        return vehiculoRepository.getOne(vehiculo_id);
    }

    @Override
    public Vehiculo getVehiculoByGps(Long vehiculo_id) {
        return getVehiculoByIdGps(vehiculo_id);
    }

    @Override
    public Vehiculo add(Vehiculo vehiculo) {
        vehiculo.setHabilitado(true);
        vehiculo.setBorrado(false);
        return (Vehiculo) addEntity(vehiculo);
    }


    @Override
    public boolean delete(Long vehiculo_id) {
        Vehiculo vehiculo = vehiculoRepository.getOne(vehiculo_id);
        vehiculo.setBorrado(true);
        return deleteEntity(vehiculo);
    }

    @Override
    public Vehiculo update(Long vehiculo_id, Vehiculo vehiculo) {
        vehiculo.setVehiculoId(vehiculo_id);
        return (Vehiculo) updateEntity(vehiculo);
    }


}
