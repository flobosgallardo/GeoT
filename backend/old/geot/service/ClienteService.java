package old.geot.service;

import com.ruyo.rest.geot.entity.Cliente;

public interface ClienteService {

    boolean add(Cliente cliente);
    Cliente findOne(Long id);
    boolean delete(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    String getAllClientes();
    boolean isUserValid(String user, String password);
}
