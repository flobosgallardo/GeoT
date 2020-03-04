package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Celular;

import java.util.Collection;

public interface RestCelularDao {

    Collection<Celular> getCelulares();
    Celular getCelular(String id);
    Celular add(Celular celular);
}
