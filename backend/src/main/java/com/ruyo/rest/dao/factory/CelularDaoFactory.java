package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.CelularDao;
import com.ruyo.rest.dao.repository.CelularRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Celular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("celularDao")
@Transactional @SuppressWarnings( "unchecked" )
public class CelularDaoFactory extends FactoryUtility implements CelularDao {

    private CelularRepository celularRepository;

    @Autowired
    public CelularDaoFactory(
            @Qualifier("celularRepository") CelularRepository celularRepository){
        this.celularRepository = celularRepository;

    }

    @Override
    public List<Celular> getCelulares() {
        return celularRepository.findAll();
    }

    @Override
    public Optional<Celular> getCelular(Long celular_id) {
        return celularRepository.findById(celular_id);
    }

    @Override
    public Celular getOneById(Long celular_id) {
        return celularRepository.getOne(celular_id);
    }

    @Override
    public Celular add(Celular celular) {
        celular.setHabilitado(true);
        celular.setBorrado(false);
        return (Celular) addEntity(celular);
    }

    @Override
    public boolean delete(Long clientecelular_id) {
        Celular celular = celularRepository.getOne(clientecelular_id);
        celular.setBorrado(true);
        return deleteEntity(celular);
    }

    @Override
    public Celular update(Long clientecelular_id, Celular celular) {
        celular.setCelularId(clientecelular_id);
        return (Celular) updateEntity(celular);
    }

    @Override
    public List getCelularesByIdCliente(Long cliente_id){
        return getCelularesByIdClient(cliente_id);
    }
}
