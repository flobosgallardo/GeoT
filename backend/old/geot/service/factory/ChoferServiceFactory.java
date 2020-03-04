package old.geot.service.factory;

import com.ruyo.rest.geot.dao.ChoferDao;
import com.ruyo.rest.geot.entity.Chofer;
import com.ruyo.rest.geot.service.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("choferService")
public class ChoferServiceFactory implements ChoferService {

    @Qualifier("choferDao")
    private ChoferDao choferDaoFactory;

    @Autowired
    public ChoferServiceFactory(ChoferDao choferDaoFactory) {
        this.choferDaoFactory = choferDaoFactory;
    }

    @Override
    public boolean add(Chofer chofer) {
        return choferDaoFactory.add(chofer);
    }

    @Override
    public Chofer findOne(Long id) {
        return choferDaoFactory.findOne(id);
    }

    @Override
    public boolean delete(Chofer chofer) {
        return choferDaoFactory.delete(chofer);
    }

    @Override
    public Chofer update(Long id, Chofer chofer) {
        return choferDaoFactory.update(id, chofer);
    }

    @Override
    public List getAllChoferes() {
        return choferDaoFactory.getAllChoferes();
    }
}
