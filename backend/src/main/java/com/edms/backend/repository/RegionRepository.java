package com.edms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edms.backend.entity.Region;
import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> findByCategoryId(int categoryId); // Fetch regions by categoryId
}
