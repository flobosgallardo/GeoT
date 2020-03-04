package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Chofer;

import java.util.Collection;

public interface RestChoferDao {

    Collection<Chofer> getChoferes();
    Chofer getChofer(String id);
}
