package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Rol;

import java.util.Collection;

public interface RestRolDao {

    Collection<Rol> getRoles();
    Rol getRol(String rol_id);
}
