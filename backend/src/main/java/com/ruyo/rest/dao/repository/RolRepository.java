package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("rolRepository")
@Transactional
public interface RolRepository extends JpaRepository<Rol, Long> {
}
