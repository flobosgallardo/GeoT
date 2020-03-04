package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolDao {

    Rol getOneById(Long rol_id);
    List<Rol> getRoles();
    Optional<Rol> getRol(Long rol_id);
    Rol add(Rol rol);
    Rol update(Long rol_id, Rol rol);
}
