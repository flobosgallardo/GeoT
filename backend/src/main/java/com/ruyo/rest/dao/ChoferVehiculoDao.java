package com.ruyo.rest.dao;

import com.ruyo.rest.entity.ChoferVehiculo;

import java.util.List;
import java.util.Optional;

public interface ChoferVehiculoDao {

    ChoferVehiculo addChoferVehiculo(ChoferVehiculo choferVehiculo);
    boolean deleteChoferVehiculo(Long chlo_id);
    ChoferVehiculo updateChoferVehiculo(Long chlo_id, ChoferVehiculo choferVehiculo);
    ChoferVehiculo getbyOne(Long chlo_id);
    Optional<ChoferVehiculo> getChoferVehiculo(Long chlo_id);
    List<ChoferVehiculo> getChoferVehiculo();

}
