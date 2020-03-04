package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("empresaRepository")
@Transactional
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
