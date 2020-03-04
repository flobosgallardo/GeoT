package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Gps;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GpsDao {

    Collection<Gps> getListGps();
    Optional<Gps> getGps(Long gps_id);
    Gps getOneById(Long gps_id);
    List getCoordByGps(Long gps_id);
    Gps addGps(Gps gps, Long vehiculo_id, Long celular_id);
    boolean delete(Long gps_id);
    Gps update(Long gps_id, Gps gps);

}
