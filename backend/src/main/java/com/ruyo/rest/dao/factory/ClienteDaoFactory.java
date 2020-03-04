package com.ruyo.rest.dao.factory;

import com.ruyo.rest.dao.ClienteDao;
import com.ruyo.rest.dao.repository.ClienteRepository;
import com.ruyo.rest.dao.factory.utilities.FactoryUtility;
import com.ruyo.rest.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository("clienteDao")
@Transactional @SuppressWarnings( "unchecked" )
public class ClienteDaoFactory extends FactoryUtility implements ClienteDao{

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteDaoFactory(@Qualifier("clienteRepository") ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> getCliente(Long cliente_id){
        return clienteRepository.findById(cliente_id);
    }

    @Override
    public Cliente add(Cliente cliente) {
        cliente.setHabilitado(true);
        cliente.setBorrado(false);
        return (Cliente) addEntity(cliente);
    }

    public Cliente getOneById(Long cliente_id){
        return clienteRepository.getOne(cliente_id);
    }

    @Override
    public boolean delete(Long cliente_id) {
        Cliente cliente = clienteRepository.getOne(cliente_id);
        cliente.setBorrado(true);
        return deleteEntity(cliente);
    }

    @Override
    public Cliente update(Long cliente_id, Cliente cliente) {
        cliente.setClienteId(cliente_id);
        return (Cliente) updateEntity(cliente);
    }

    @Override
    public Cliente userValidate(String usuario, String contrasena) {
        return getClienteByParams(usuario, contrasena);
    }

    @Override
    public Cliente getClienteByUsuario(String usuario) {
        return getClienteByUser(usuario);
    }

    @Override
    public Cliente getClienteByCorreo(String correo) {
        return getClienteByEmail(correo);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }



}
