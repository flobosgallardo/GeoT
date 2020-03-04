package com.ruyo.rest.dao.factory;


import com.ruyo.rest.dao.RolDao;

import com.ruyo.rest.dao.repository.RolRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("rolDao")
@Transactional
@SuppressWarnings( "unchecked" )
public class RolDaoFactory extends FactoryUtility implements RolDao {

    private RolRepository rolRepository;

    @Autowired
    public RolDaoFactory(@Qualifier("rolRepository") RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol getOneById(Long rol_id) {
        return rolRepository.getOne(rol_id);
    }

    @Override
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> getRol(Long rol_id) {
        return rolRepository.findById(rol_id);
    }

    @Override
    public Rol add(Rol rol) {
        return (Rol) addEntity(rol);
    }


    @Override
    public Rol update(Long rol_id, Rol rol) {
        rol.setRolId(rol_id);
        return (Rol) updateEntity(rol);
    }
}
