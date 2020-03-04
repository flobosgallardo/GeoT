package old.geot.dao;


import com.ruyo.rest.geot.entity.Cliente;

public interface ClienteDao  {

    boolean add(Cliente cliente);
    Cliente findOne(Long id);
    boolean delete(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    String getAllClientes();
    boolean isClienteValid(String user, String password);
}
