package old.geot.service;

import com.ruyo.rest.geot.entity.Empresa;

import java.util.List;

public interface EmpresaService {

    boolean add(Empresa empresa);
    Empresa findOne(Long id);
    boolean delete(Empresa empresa);
    Empresa update(Long id, Empresa empresa);
    List<Empresa> getAllEmpresa();
}
