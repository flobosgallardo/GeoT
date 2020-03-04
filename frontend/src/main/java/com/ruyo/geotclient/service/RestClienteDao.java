package com.ruyo.geotclient.service;

import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.model.Rol;

import java.util.Collection;

public interface RestClienteDao {

    Collection<Cliente> getClientes();
    Cliente getCliente(String id);
    Cliente add(Cliente cliente);
    Cliente userValidate(String usuario, String contrasena);
    Cliente getClienteByUsuario(String usuario);
    Cliente getClienteByCorreo(String correo);
}
