package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaDao {

    List<Empresa> getEmpresas();
    Optional<Empresa> getEmpresa(Long empresa_id);
    Empresa getOneById(Long empresa_id);
    Empresa add(Long cliente_id, Empresa empresa);
    boolean delete(Long empresa_id);
    Empresa update(Long empresa_id, Empresa empresa);
}
