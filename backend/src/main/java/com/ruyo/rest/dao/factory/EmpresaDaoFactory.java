package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.ClienteDao;
import com.ruyo.rest.dao.EmpresaDao;
import com.ruyo.rest.dao.repository.EmpresaRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Cliente;
import com.ruyo.rest.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("empresaDao")
@Transactional @SuppressWarnings( "unchecked" )
public class EmpresaDaoFactory extends FactoryUtility implements EmpresaDao {

    private EmpresaRepository empresaRepository;
    private ClienteDao clienteDaoFactory;

    @Autowired
    public EmpresaDaoFactory(@Qualifier("empresaRepository") EmpresaRepository empresaRepository,
                             @Qualifier("clienteDao") ClienteDao clienteDaoFactory) {
        this.empresaRepository = empresaRepository;
        this.clienteDaoFactory = clienteDaoFactory;
    }

    @Override
    public List<Empresa> getEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> getEmpresa(Long empresa_id) {
        return empresaRepository.findById(empresa_id);
    }

    @Override
    public Empresa getOneById(Long empresa_id) {
        return empresaRepository.getOne(empresa_id);
    }

    @Override
    public Empresa add(Long cliente_id, Empresa empresa) {
        Cliente cliente = clienteDaoFactory.getOneById(cliente_id);
        empresa.setCliente(cliente);
        empresa.setHabilitado(true);
        empresa.setBorrado(false);
        cliente.getEmpresas().add(empresa);
        return (Empresa) addEntity(empresa);
    }


    @Override
    public boolean delete(Long cliente_id) {
        Empresa empresa = empresaRepository.getOne(cliente_id);
        empresa.setBorrado(true);
        return deleteEntity(empresa);
    }

    @Override
    public Empresa update(Long empresa_id, Empresa empresa) {
        empresa.setEmpresaId(empresa_id);
        return (Empresa) updateEntity(empresa);
    }
}
