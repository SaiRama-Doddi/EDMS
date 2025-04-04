package com.edms.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edms.backend.entity.Category;
import com.edms.backend.entity.Region;
import com.edms.backend.entity.StationCode;
import com.edms.backend.entity.StationType;
import com.edms.backend.entity.UploadType;
import com.edms.backend.service.CategoryService;
import com.edms.backend.service.RegionService;
import com.edms.backend.service.StationCodeService;
import com.edms.backend.service.StationTypeService;
import com.edms.backend.service.UploadTypeService;


@RestController
@RequestMapping("/api/details")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;
    private RegionService regionService;
    private final StationCodeService stationCodeService;
    private final StationTypeService stationTypeService;
     private final UploadTypeService uploadTypeService;

  
    public CategoryController(CategoryService categoryService,RegionService regionService, StationCodeService stationCodeService, StationTypeService stationTypeService, UploadTypeService uploadTypeService) {
        this.uploadTypeService=uploadTypeService;
        this.stationTypeService = stationTypeService;
        this.stationCodeService = stationCodeService;
        this.regionService = regionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/regions")
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable int id) {
        Region region = regionService.getRegionById(id);
        return (region != null) ? ResponseEntity.ok(region) : ResponseEntity.notFound().build();
    }


     // New API: Get regions by category ID
     @GetMapping("/regions/category/{categoryId}")
     public ResponseEntity<List<Region>> getRegionsByCategory(@PathVariable int categoryId) {
         List<Region> regions = regionService.getRegionsByCategoryId(categoryId);
         return ResponseEntity.ok(regions);
     }


     @GetMapping("/stations")
    public List<StationCode> getAllStationCodes() {
        return stationCodeService.getAllStationCodes();
    }


    @GetMapping("/stations/{id}")
    public ResponseEntity<StationCode> getStationCodeById(@PathVariable int id) {
        StationCode stationCode = stationCodeService.getStationCodeById(id);
        return (stationCode != null) ? ResponseEntity.ok(stationCode) : ResponseEntity.notFound().build();
    }


    @GetMapping("/stations/regions/{regionId}")
    public List<StationCode> getStationCodesByRegion(@PathVariable int regionId) {
        return stationCodeService.getStationCodesByRegion(regionId);
    }

    @GetMapping("/stations/category/{categoryId}")
    public List<StationCode> getStationCodesByCategory(@PathVariable int categoryId) {
        return stationCodeService.getStationCodesByCategory(categoryId);
    }

      // Get all station types
    @GetMapping("/station-types")
    public List<StationType> getAllStationTypes() {
        return stationTypeService.getAllStationTypes();
    }

     // Get station type by ID
     @GetMapping("/station-types/{id}")
     public ResponseEntity<StationType> getStationTypeById(@PathVariable int id) {
         Optional<StationType> stationType = stationTypeService.getStationTypeById(id);
         return stationType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
     }

       // Get station type by stationCodeId
    @GetMapping("/station-types/stations/{stationCodeId}")
    public ResponseEntity<StationType> getStationTypeByStationCodeId(@PathVariable int stationCodeId) {
        Optional<StationType> stationType = stationTypeService.getStationTypeByStationCodeId(stationCodeId);
        return stationType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


      @GetMapping("/upload-types")
    public List<UploadType> getAllUploadTypes() {
        return uploadTypeService.getAllUploadTypes();
    }

      // Get upload type by ID
      @GetMapping("upload-types/{id}")
      public ResponseEntity<UploadType> getUploadTypeById(@PathVariable int id) {
          Optional<UploadType> uploadType = uploadTypeService.getUploadTypeById(id);
          return uploadType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
      }

         // Get upload types by category ID
    @GetMapping("upload-types/category/{categoryId}")
    public List<UploadType> getUploadTypesByCategoryId(@PathVariable int categoryId) {
        return uploadTypeService.getUploadTypesByCategoryId(categoryId);
    }



}

