package old.geot.dao;

import com.ruyo.rest.geot.entity.Empresa;

import java.util.List;

public interface EmpresaDao {

    boolean add(Empresa empresa);
    Empresa findOne(Long id);
    boolean delete(Empresa empresa);
    Empresa update(Long id, Empresa empresa);
    List<Empresa> getAllEmpresa();
}
