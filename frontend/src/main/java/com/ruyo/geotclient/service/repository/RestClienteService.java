package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.security.EncryptPasswordHash;
import com.ruyo.geotclient.service.RestClienteDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestClienteService extends AbstractTemplate implements RestClienteDao {

 
    protected RestClienteService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public List<Cliente> getClientes(){
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getCLIENTE(), Cliente[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Cliente getCliente(String id){
        return getRestTemplate().
                getForObject(URL.getCLIENTE_BYID(id), Cliente.class);
    }

    @Override
    public Cliente add(Cliente cliente) {
        cliente.setContrasena(new EncryptPasswordHash().encrypt(cliente.getContrasena()));
        return getRestTemplate().postForObject(URL.getCLIENTE(), cliente, Cliente.class);


    }

    @Override
    public Cliente userValidate(String usuario, String contrasena) {
        try {
            return getRestTemplate().
                    getForObject(URL.getCLIENTEBYPARAM(usuario, contrasena), Cliente.class);
        }catch (HttpServerErrorException e){
            return null;
        }
    }

    @Override
    public Cliente getClienteByUsuario(String usuario) {
        return getRestTemplate().
                getForObject(URL.getCLIENTEBYUSUARIO(usuario), Cliente.class);
    }

    @Override
    public Cliente getClienteByCorreo(String correo) {
        return getRestTemplate().
                getForObject(URL.getCLIENTEBYEMAIL(correo), Cliente.class);
    }


}
