package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Celular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("celularRepository")
@Transactional
public interface CelularRepository extends JpaRepository<Celular, Long> {
}
