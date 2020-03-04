package old.geot.dao;


import com.ruyo.rest.geot.entity.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository("empresaTestDao")
@Transactional
public interface EmpresaTestDao extends CrudRepository<Empresa, Long> {

    Empresa save(Empresa empresa);
    Collection<Empresa> findAll();
    Optional<Empresa> findById(Long id);
}
