package com.edms.backend.controller;

import com.edms.backend.entity.FormEntity;
import com.edms.backend.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping
    public List<FormEntity> getAllForms() {
        return formService.getAllForms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormEntity> getFormById(@PathVariable int id) {
        Optional<FormEntity> form = formService.getFormById(id);
        return form.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stations/{stationCodeId}")
    public List<FormEntity> getByStationCode(@PathVariable int stationCodeId) {
        return formService.getByStationCode(stationCodeId);
    }

    @GetMapping("/document/{documentType}")
    public Map<String, Object> getByDocumentType(@PathVariable String documentType) {
        return formService.getByDocumentType(documentType);
    }

    @GetMapping("/user/{userId}")
    public List<FormEntity> getByUserId(@PathVariable int userId) {
        return formService.getByUserId(userId);
    }

    @GetMapping("/region/{regionId}")
    public List<FormEntity> getByRegion(@PathVariable int regionId) {
        return formService.getByRegion(regionId);
    }

    @GetMapping("/drawing/{drawingNo}")
    public List<FormEntity> getByDrawingNo(@PathVariable String drawingNo) {
        return formService.getByDrawingNo(drawingNo);
    }

    @PostMapping
    public ResponseEntity<FormEntity> createForm(@RequestBody FormEntity formEntity) {
        FormEntity savedForm = formService.saveForm(formEntity);
        return ResponseEntity.ok(savedForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable int id) {
        formService.deleteForm(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/category/{categoryId}")
    public Map<String, Object> getByCategory(@PathVariable int categoryId) {
        return formService.getByCategoryWithCount(categoryId);
    }
}
