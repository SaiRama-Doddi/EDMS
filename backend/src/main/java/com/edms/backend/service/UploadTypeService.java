package com.edms.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edms.backend.entity.UploadType;
import com.edms.backend.repository.UploadTypeRepository;

@Service
public class UploadTypeService {


    
    private final UploadTypeRepository uploadTypeRepository;

     @Autowired
    public UploadTypeService(UploadTypeRepository uploadTypeRepository) {
        this.uploadTypeRepository = uploadTypeRepository;
    }


     // Get all upload types
    public List<UploadType> getAllUploadTypes() {
        return uploadTypeRepository.findAll();
    }

    // Get upload type by ID
    public Optional<UploadType> getUploadTypeById(int id) {
        return uploadTypeRepository.findById(id);
    }

    // Get upload types by category ID
    public List<UploadType> getUploadTypesByCategoryId(int categoryId) {
        return uploadTypeRepository.findByCategoryId(categoryId);
    }
    
}
