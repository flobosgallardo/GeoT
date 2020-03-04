package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("clienteRepository")
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
