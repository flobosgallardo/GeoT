package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Empresa;

import java.util.Collection;

public interface RestEmpresaDao {

    Collection<Empresa> getEmpresas();
    Empresa getEmpresa(String id);
    Empresa add(Empresa empresa);
}
