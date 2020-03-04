package old.geot.service.factory;

import com.ruyo.rest.geot.dao.EmpresaDao;
import com.ruyo.rest.geot.entity.Empresa;
import com.ruyo.rest.geot.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empresaService")
public class EmpresaServiceFactory implements EmpresaService {

    @Qualifier("empresaDao")
    private EmpresaDao empresaDaoFactory;

    @Autowired
    public EmpresaServiceFactory(EmpresaDao empresaDaoFactory){
        this.empresaDaoFactory = empresaDaoFactory;
    }

    @Override
    public boolean add(Empresa empresa) {
        return empresaDaoFactory.add(empresa);
    }

    @Override
    public Empresa findOne(Long id) {
        return empresaDaoFactory.findOne(id);
    }

    @Override
    public boolean delete(Empresa empresa) {
        return empresaDaoFactory.delete(empresa);
    }

    @Override
    public Empresa update(Long id, Empresa empresa) {
        return empresaDaoFactory.update(id, empresa);
    }

    @Override
    public List<Empresa> getAllEmpresa() {
        return empresaDaoFactory.getAllEmpresa();
    }
}
