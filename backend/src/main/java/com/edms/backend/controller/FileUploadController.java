package com.edms.backend.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.edms.backend.entity.Category;
import com.edms.backend.entity.Region;
import com.edms.backend.entity.StationCode;
import com.edms.backend.entity.StationEnum;
import com.edms.backend.entity.StationType;
import com.edms.backend.entity.UploadType;
import com.edms.backend.entity.UploadedFile;
import com.edms.backend.entity.User;
import com.edms.backend.repository.UploadedFileRepository;
import com.edms.backend.repository.UserRepository;
import com.edms.backend.service.CategoryService;
import com.edms.backend.service.FileUploadService;
import com.edms.backend.service.RegionService;
import com.edms.backend.service.StationCodeService;
import com.edms.backend.service.StationTypeService;
import com.edms.backend.service.UploadTypeService;
import com.edms.backend.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

    @Autowired
    private final UploadedFileRepository uploadedFileRepository;
    @Autowired
    private FileUploadService fileUploadService;

    @Autowired // ✅ Inject CategoryService instead of CategoryRepository
    private CategoryService categoryService;


    @Autowired
    private RegionService regionService;

    @Autowired
    private StationCodeService stationCodeService;


    @Autowired
    private StationTypeService stationTypeService;


    @Autowired
     private UploadTypeService uploadTypeService;


     private UserService userService;


    // Constructor Injection for better testability and maintainability
    @Autowired
    private UserRepository userRepository;


    // Constructor Injection for better testability and maintainability
    @Autowired
    public FileUploadController(UploadedFileRepository uploadedFileRepository, UserRepository userRepository) {
        this.uploadedFileRepository = uploadedFileRepository;
        this.userRepository = userRepository;
    }



public FileUploadController(
    CategoryService categoryService, 
    FileUploadService fileUploadService, 
    UploadedFileRepository uploadedFileRepository,
    RegionService regionService,
    StationCodeService stationCodeService,
    StationTypeService stationTypeService,
    UploadTypeService uploadTypeService,
    UserService userService
) {
    this.userService = userService;
    this.uploadTypeService = uploadTypeService;
    this.categoryService = categoryService;
    this.fileUploadService = fileUploadService;
    this.uploadedFileRepository = uploadedFileRepository;
    this.regionService = regionService;
    this.stationCodeService = stationCodeService;
    this.stationTypeService = stationTypeService;
}

@PostMapping("/upload")
public ResponseEntity<String> uploadFile(
        @RequestParam("serialNo") String serialNo,
        @RequestParam("drawingNo") String drawingNo,
        @RequestParam("sheet") String sheet,
        @RequestParam("rev") String rev,
        @RequestParam("stationId") Integer stationId,
        @RequestParam("stationTypeId") Integer stationTypeId,
        @RequestParam("categoryId") Integer categoryId,
        @RequestParam("regionId") Integer regionId,
        @RequestParam("uploadTypeId") Integer uploadTypeId,
        @RequestParam("userId") Integer userId,
        @RequestParam("file") MultipartFile file) {

    try {
        System.out.println("🟢 Received stationTypeId: " + stationTypeId); // Debugging

         // ✅ Fetch Station Type
        // ✅ Fetch Station Type
Optional<StationType> stationTypeOptional = stationTypeService.getStationTypeById(stationTypeId);
if (stationTypeOptional.isEmpty()) {
    throw new RuntimeException("Invalid Station Type ID: " + stationTypeId);
}
StationType stationType = stationTypeOptional.get();
StationEnum stationEnum = stationType.getType(); // ✅ Get ENUM Value
System.out.println("✅ Retrieved Station Type: " + stationType.getId() + " - " + stationType.getType()); // Debugging


        // ✅ Fetch Other Entities
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Category category = categoryService.getCategoryById(categoryId);
        Region region = regionService.getRegionById(regionId);
        StationCode station = stationCodeService.getStationCodeById(stationId);
        UploadType uploadType = uploadTypeService.getUploadTypeById(uploadTypeId)
                .orElseThrow(() -> new RuntimeException("Invalid Upload Type ID: " + uploadTypeId));

        // ✅ Save File
        String filePath = fileUploadService.saveFile(file);
        System.out.println("File saved at: " + filePath);

        // ✅ Create UploadedFile Entity
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setSerialNo(serialNo);
        uploadedFile.setDrawingNo(drawingNo);
        uploadedFile.setSheet(sheet);
        uploadedFile.setRev(rev);
        uploadedFile.setFileType(file.getContentType());
        uploadedFile.setFilePath(filePath);
      // ✅ Assign Station Type to UploadedFile
uploadedFile.setStationType(stationType);
System.out.println("✅ Stored Station Type in Database: " + uploadedFile.getStationType().getType()); // Debugging
uploadedFile.setStationTypeEnum(stationEnum); // ✅ Store ENUM value

        uploadedFile.setCategory(category);
        uploadedFile.setRegion(region);
        uploadedFile.setStation(station);
        uploadedFile.setUploadType(uploadType);
        uploadedFile.setUser(user);

        // ✅ Save to Database
        UploadedFile savedFile = uploadedFileRepository.save(uploadedFile);
        System.out.println("✅ Stored Station Type in Database: " + savedFile.getStationType().getType());

        System.out.println("✅ File metadata saved successfully!");
        System.out.println("✅ Stored Station Type in Database: " + savedFile.getStationType().getType());

        return ResponseEntity.ok("File uploaded successfully");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("File upload failed: " + e.getMessage());
    }
}



}