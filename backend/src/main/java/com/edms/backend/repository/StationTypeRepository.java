package com.edms.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edms.backend.entity.StationType;

@Repository
public interface StationTypeRepository extends JpaRepository<StationType, Integer> {
    Optional<StationType> findByStation_Id(int stationId);
}

