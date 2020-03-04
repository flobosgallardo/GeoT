package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Celular;

import java.util.List;
import java.util.Optional;

public interface CelularDao {

    List<Celular> getCelulares();
    Optional<Celular> getCelular(Long clientecelular_id);
    Celular getOneById(Long clientecelular_id);
    Celular add(Celular celular);
    boolean delete(Long empresa_id);
    Celular update(Long clientecelular_id, Celular celular);
    List getCelularesByIdCliente(Long cliente_id);
}
