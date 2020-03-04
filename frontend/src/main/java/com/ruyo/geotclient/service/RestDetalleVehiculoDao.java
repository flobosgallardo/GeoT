package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.DetalleVehiculo;

import java.util.Collection;

public interface RestDetalleVehiculoDao {

    Collection<DetalleVehiculo> getDetallesVehiculos();
    DetalleVehiculo getDetalleVehiculo(String id);
}
