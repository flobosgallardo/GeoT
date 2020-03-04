package com.ruyo.rest.dao.repository;

import com.ruyo.rest.entity.Gps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("gpsRepository")
@Transactional
public interface GpsRepository extends JpaRepository<Gps, Long> {
}
