package com.edms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edms.backend.entity.StationType;
import com.edms.backend.repository.StationCodeRepository;
import com.edms.backend.repository.StationTypeRepository;

@Service
public class StationTypeService {


      private final StationTypeRepository stationTypeRepository;
      private final StationCodeRepository stationCodeRepository;


     
    public StationTypeService(StationTypeRepository stationTypeRepository, StationCodeRepository stationCodeRepository) {
        this.stationCodeRepository = stationCodeRepository;
        this.stationTypeRepository = stationTypeRepository;
    }

      // Get all station types
    public List<StationType> getAllStationTypes() {
        return stationTypeRepository.findAll();
    }

    // Get station type by ID
    public Optional<StationType> getStationTypeById(int id) {
        return stationTypeRepository.findById(id);
    }

    // Save a new station type
    public StationType saveStationType(StationType stationType) {
        return stationTypeRepository.save(stationType);
    }

      // Get station type by stationCodeId
      public Optional<StationType> getStationTypeByStationCodeId(int stationCodeId) {
        return stationTypeRepository.findByStation_Id(stationCodeId);
    }
    
    
}
