package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("vehiculoRepository")
@Transactional
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
