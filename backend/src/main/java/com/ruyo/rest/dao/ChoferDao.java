package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Chofer;

import java.util.List;
import java.util.Optional;

public interface ChoferDao {

    List<Chofer> getChoferes();
    Optional<Chofer> getChofer(Long chofer_id);
    Chofer getOneById(Long chofer_id);
    Chofer add(Long empresa_id, Chofer chofer);
    boolean delete(Long chofer_id);
    Chofer update(Long chofer_id, Chofer chofer);
}
