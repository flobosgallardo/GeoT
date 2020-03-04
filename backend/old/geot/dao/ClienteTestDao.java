package old.geot.dao;

import com.ruyo.rest.geot.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("clienteTestDao")
@Transactional
public interface ClienteTestDao extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
}
