package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {
}
