package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("choferRepository")
@Transactional
public interface ChoferRepository extends JpaRepository<Chofer, Long> {
}
