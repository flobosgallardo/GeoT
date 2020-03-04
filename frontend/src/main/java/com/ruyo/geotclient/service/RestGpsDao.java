package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Gps;

import java.util.Collection;

public interface RestGpsDao {

    Collection<Gps> getGpsList();
    Gps getGps(String id);
}
