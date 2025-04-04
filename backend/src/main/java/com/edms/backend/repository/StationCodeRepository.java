package com.edms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edms.backend.entity.StationCode;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationCodeRepository extends JpaRepository<StationCode, Integer> {
    Optional<StationCode> findByCode(String code);
    List<StationCode> findByRegionId(int regionId);
    List<StationCode> findByCategoryId(int categoryId);
   
}
