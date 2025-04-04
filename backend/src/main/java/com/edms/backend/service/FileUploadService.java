package com.edms.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edms.backend.entity.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Ensure directory exists
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.write(filePath, file.getBytes());

        return filePath.toString(); // Return the full file path
    }

    public void uploadFile(User user, MultipartFile file) {
        // Ensure the user exists before proceeding
        if (user == null) {
            throw new RuntimeException("Invalid user for upload");
        }

        // Process file upload and associate it with user
        System.out.println("Uploading file for user: " + user.getEmail());

        // Logic to save file metadata, user details, etc.
    }
}

