package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.ChoferVehiculoDao;
import com.ruyo.rest.dao.repository.ChoferVehiculoRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.ChoferVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("choferVehiculoDao")
@Transactional
@SuppressWarnings( "unchecked" )
public class ChoferVehiculoFactory extends FactoryUtility implements ChoferVehiculoDao{

    private ChoferVehiculoRepository choferVehiculoRepository;

    @Autowired
    public ChoferVehiculoFactory(
            @Qualifier("choferVehiculoRepository") ChoferVehiculoRepository choferVehiculoRepository) {
        this.choferVehiculoRepository = choferVehiculoRepository;
    }


    @Override
    public ChoferVehiculo addChoferVehiculo(ChoferVehiculo choferVehiculo) {
        choferVehiculo.setHabilitado(true);
        choferVehiculo.setBorrado(false);
        return (ChoferVehiculo) addEntity(choferVehiculo);
    }

    @Override
    public boolean deleteChoferVehiculo(Long chlo_id) {
        ChoferVehiculo getChoferVehiculo = choferVehiculoRepository.getOne(chlo_id);
        getChoferVehiculo.setBorrado(true);
        return deleteEntity(getChoferVehiculo);
    }

    @Override
    public ChoferVehiculo updateChoferVehiculo(Long chlo_id, ChoferVehiculo choferVehiculo) {
        choferVehiculo.setChlo_id(chlo_id);
        return (ChoferVehiculo) updateEntity(choferVehiculo);
    }

    @Override
    public ChoferVehiculo getbyOne(Long chlo_id) {
        return choferVehiculoRepository.getOne(chlo_id);
    }

    @Override
    public Optional<ChoferVehiculo> getChoferVehiculo(Long chlo_id) {
        return choferVehiculoRepository.findById(chlo_id);
    }

    @Override
    public List<ChoferVehiculo> getChoferVehiculo() {
        return choferVehiculoRepository.findAll();
    }
}
