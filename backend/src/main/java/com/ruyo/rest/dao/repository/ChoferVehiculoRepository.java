package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.ChoferVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("choferVehiculoRepository")
@Transactional
public interface ChoferVehiculoRepository extends JpaRepository<ChoferVehiculo, Long> {
}
