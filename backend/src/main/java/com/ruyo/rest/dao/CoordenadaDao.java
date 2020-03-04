package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Coordenada;

import java.util.List;

public interface CoordenadaDao {

    Coordenada add(Coordenada coordenada);
    List getCoordenadaByIdGps(Long gps_id);
    void update(Coordenada coordenada);

}
