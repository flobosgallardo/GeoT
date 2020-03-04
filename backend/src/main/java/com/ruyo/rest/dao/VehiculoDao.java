package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoDao  {

    List<Vehiculo> getVehiculos();
    Optional<Vehiculo> getVehiculo(Long vehiculo_id);
    Vehiculo getOneById(Long vehiculo_id);
    Vehiculo getVehiculoByGps(Long vehiculo_id);
    Vehiculo add(Vehiculo vehiculo);
    boolean delete(Long vehiculo_id);
    Vehiculo update(Long vehiculo_id, Vehiculo vehiculo);
}
