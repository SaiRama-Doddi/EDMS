package com.edms.backend.service;
import org.springframework.stereotype.Service;
import com.edms.backend.entity.Region;
import com.edms.backend.repository.RegionRepository;
import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(int id) {
        return regionRepository.findById(id).orElse(null);
    }

    // Fetch regions by category ID
    public List<Region> getRegionsByCategoryId(int categoryId) {
        return regionRepository.findByCategoryId(categoryId);
    }
}
