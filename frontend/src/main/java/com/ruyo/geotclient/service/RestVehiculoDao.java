package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Vehiculo;

import java.util.Collection;

public interface RestVehiculoDao {

    Collection<Vehiculo> getVehiculos();
    Vehiculo getVehiculo(String id);
}
