package com.edms.backend.service;

import com.edms.backend.entity.FormEntity;
import com.edms.backend.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public List<FormEntity> getAllForms() {
        return formRepository.findAll();
    }

    public Optional<FormEntity> getFormById(int id) {
        return formRepository.findById(id);
    }

    public List<FormEntity> getByStationCode(int stationCodeId) {
        return formRepository.findByStationCode_Id(stationCodeId);
    }

    public Map<String, Object> getByDocumentType(String documentType) {
        List<FormEntity> forms = formRepository.findByDocumentTypeContainingIgnoreCase(documentType);
        long count = formRepository.countByDocumentTypeContainingIgnoreCase(documentType);
    
        Map<String, Object> response = new HashMap<>();
        response.put("count", count);
        response.put("results", forms != null ? forms : List.of()); // Avoid returning null
        return response;
    }
    
    public List<FormEntity> getByUserId(int userId) {
        return formRepository.findByUser_Id(userId);
    }

    public List<FormEntity> getByRegion(int regionId) {
        return formRepository.findByRegionId(regionId);
    }

    public List<FormEntity> getByDrawingNo(String drawingNo) {
        return formRepository.findByDrawingNoContainingIgnoreCase(drawingNo);
    }

    public FormEntity saveForm(FormEntity formEntity) {
        return formRepository.save(formEntity);
    }

    public void deleteForm(int id) {
        formRepository.deleteById(id);
    }



    public List<FormEntity> getByCategory(int categoryId) {
        return formRepository.findByCategoryId(categoryId);
    }

    public Map<String, Object> getByCategoryWithCount(int categoryId) {
        List<FormEntity> forms = formRepository.findByCategoryId(categoryId);
        long count = formRepository.countByCategoryId(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("count", count);
        response.put("results", forms);

        return response;
    }
}
