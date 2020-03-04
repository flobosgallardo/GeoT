package com.ruyo.rest.dao;

import com.ruyo.rest.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDao {

    List<Cliente> getClientes();
    Optional<Cliente> getCliente(Long cliente_id);
    Cliente add(Cliente cliente);
    Cliente getOneById(Long cliente_id);
    boolean delete(Long cliente_id);
    Cliente update(Long cliente_id, Cliente cliente);
    Cliente userValidate(String usuario, String contrasena);
    Cliente getClienteByUsuario(String usuario);
    Cliente getClienteByCorreo(String correo);
}
