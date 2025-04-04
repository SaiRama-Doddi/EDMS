package com.edms.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edms.backend.entity.StationCode;
import com.edms.backend.repository.CategoryRepository;
import com.edms.backend.repository.RegionRepository;
import com.edms.backend.repository.StationCodeRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor  // Lombok auto-generates constructor
public class StationCodeService {
    private final StationCodeRepository stationCodeRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;

    public List<StationCode> getAllStationCodes() {
        return stationCodeRepository.findAll();
    }


    public StationCode getStationCodeById(int id) {
        return stationCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with ID: " + id));
    }

    public List<StationCode> getStationCodesByRegion(int regionId) {
        return stationCodeRepository.findByRegionId(regionId);
    }

    public List<StationCode> getStationCodesByCategory(int categoryId) {
        return stationCodeRepository.findByCategoryId(categoryId);
    }
}

