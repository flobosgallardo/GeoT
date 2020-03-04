package old.geot.service.factory;

import com.ruyo.rest.geot.dao.ClienteDao;
import com.ruyo.rest.geot.entity.Cliente;
import com.ruyo.rest.geot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("clienteService")
public class ClienteServiceFactory implements ClienteService {

    @Qualifier("clienteDao")
    private ClienteDao clienteDaoFactory;

    @Autowired
    public ClienteServiceFactory(ClienteDao clienteDaoFactory){
        this.clienteDaoFactory = clienteDaoFactory;
    }

    @Override
    public boolean add(Cliente cliente) {
        return clienteDaoFactory.add(cliente);
    }

    @Override
    public Cliente findOne(Long id) {
        return clienteDaoFactory.findOne(id);
    }

    @Override
    public boolean delete(Cliente cliente) {
        return clienteDaoFactory.delete(cliente);
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        return clienteDaoFactory.update(id, cliente);
    }

    @Override
    public String getAllClientes() {
        return clienteDaoFactory.getAllClientes();
    }


    @Override
    public boolean isUserValid(String user, String password) {
        return clienteDaoFactory.isClienteValid(user,password);
    }
}
