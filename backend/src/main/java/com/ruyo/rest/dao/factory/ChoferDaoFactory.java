package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.ChoferDao;
import com.ruyo.rest.dao.EmpresaDao;
import com.ruyo.rest.dao.repository.ChoferRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Chofer;
import com.ruyo.rest.entity.Empresa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("choferDao")
@Transactional @SuppressWarnings( "unchecked" )
public class ChoferDaoFactory extends FactoryUtility implements ChoferDao {

    private ChoferRepository choferRepository;
    private EmpresaDao empresaDao;

    public ChoferDaoFactory(@Qualifier("choferRepository") ChoferRepository choferRepository,
                            EmpresaDao empresaDao){
        this.choferRepository = choferRepository;
        this.empresaDao = empresaDao;
    }

    @Override
    public List<Chofer> getChoferes() {
        return choferRepository.findAll();
    }

    @Override
    public Optional<Chofer> getChofer(Long chofer_id) {
        return choferRepository.findById(chofer_id);
    }

    @Override
    public Chofer getOneById(Long chofer_id) {
        return choferRepository.getOne(chofer_id);
    }

    @Override
    public Chofer add(Long empresa_id, Chofer chofer) {
        Empresa empresa = empresaDao.getOneById(empresa_id);
        chofer.setEmpresa(empresa);
        chofer.setHabilitado(true);
        chofer.setBorrado(false);
        empresa.getChoferes().add(chofer);
        return (Chofer) addEntity(chofer);
    }

    @Override
    public boolean delete(Long chofer_id) {
        Chofer chofer = choferRepository.getOne(chofer_id);
        chofer.setBorrado(true);
        return deleteEntity(chofer);
    }

    @Override
    public Chofer update(Long chofer_id, Chofer chofer) {
        chofer.setChoferId(chofer_id);
        return (Chofer) updateEntity(chofer);
    }
}
