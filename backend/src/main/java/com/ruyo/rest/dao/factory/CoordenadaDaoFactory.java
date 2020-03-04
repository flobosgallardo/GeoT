package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.CoordenadaDao;
import com.ruyo.rest.dao.repository.CoordenadaRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Coordenada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("coordenadaDao")
@Transactional
@SuppressWarnings( "unchecked" )
public class CoordenadaDaoFactory extends FactoryUtility implements CoordenadaDao {

    @Override
    public Coordenada add(Coordenada coordenada) {
        return (Coordenada) addEntity(coordenada);
    }

    @Override
    public List getCoordenadaByIdGps(Long gps_id) {
        return getCoordenadas(gps_id);
    }

    @Override
    public void update(Coordenada coordenada) {
        updateEntity(coordenada);
    }


}
