package com.edms.backend.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edms.backend.dto.LoginRequest;
import com.edms.backend.entity.Role;
import com.edms.backend.entity.User;
import com.edms.backend.repository.UserRepository;
import com.edms.backend.service.EmailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    
    private EmailService emailService;

     // ✅ Use Constructor Injection
     public AuthController(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {

            response.put("userId", user.get().getId()); // ✅ Added userId
            response.put("message", "Login successful");
            response.put("role", user.get().getRole().name());
            return response;
        }

        response.put("message", "Invalid credentials");
        return response;
    }



    @PostMapping("/create-draftsman")
    public ResponseEntity<Map<String, Object>> createDraftsman(
        @RequestBody User newUser,
        @RequestHeader Map<String, String> headers
    ) {
        Map<String, Object> response = new HashMap<>();
    
        // 🔍 Debug: Log received headers
        System.out.println("Received Headers: " + headers);
    
        String role = headers.get("role"); // Case-sensitive
        if (role == null) {
            System.out.println("❌ Role header is missing!");
            response.put("message", "Forbidden: Role header is missing");
            return ResponseEntity.status(403).body(response);
        }
    
        // ✅ Convert role to uppercase for safety
        if (!role.equalsIgnoreCase("HEXTA")) {
            System.out.println("❌ Unauthorized attempt! Only HEXTA can create Draftsman.");
            response.put("message", "Forbidden: Only HEXTA can create Draftsman users");
            return ResponseEntity.status(403).body(response);
        }
    
        // 🔍 Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            System.out.println("❌ Email already exists: " + newUser.getEmail());
            response.put("message", "Email already exists");
            return ResponseEntity.status(400).body(response);
        }
    
        // 🔍 Debug: Before saving the new user
        System.out.println("✅ Creating new Draftsman with email: " + newUser.getEmail());
    
        // ✅ Save new Draftsman user
        newUser.setRole(Role.DRAFTSMAN);
        userRepository.save(newUser);
    
        System.out.println("✅ Draftsman Created Successfully: " + newUser.getEmail());
    
          // ✅ Send email to the new Station Master
          emailService.sendDraftsmanCredentials(newUser.getEmail(), newUser.getPassword());
    
          System.out.println("✅ Draftsman Created & Email Sent: " + newUser.getEmail());
      
          response.put("message", "Draftsman  created successfully. Credentials sent via email.");
          return ResponseEntity.ok(response);
    }
    

    @PostMapping("/create-stationmaster")
    public ResponseEntity<Map<String, Object>> createStationMaster(
            @RequestBody User newUser,
            @RequestHeader Map<String, String> headers
    ) {
        Map<String, Object> response = new HashMap<>();
    
        // 🔍 Debug: Log received headers
        System.out.println("Received Headers: " + headers);
    
        String role = headers.get("role"); // Case-sensitive
        if (role == null) {
            System.out.println("❌ Role header is missing!");
            response.put("message", "Forbidden: Role header is missing");
            return ResponseEntity.status(403).body(response);
        }
    
        // ✅ Convert role to uppercase for safety
        if (!role.equalsIgnoreCase("GAIL")) {
            System.out.println("❌ Unauthorized attempt! Only GAIL can create Station Masters.");
            response.put("message", "Forbidden: Only GAIL can create Station Master users");
            return ResponseEntity.status(403).body(response);
        }
    
        // 🔍 Check if email already exists in the database
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            System.out.println("❌ Error: Email already exists in DB -> " + newUser.getEmail());
            response.put("message", "Error: Email already exists. Use a different email.");
            return ResponseEntity.status(400).body(response);
        }
    
        // 🔍 Debug: Before saving the new user
        System.out.println("✅ Creating new Station Master with email: " + newUser.getEmail());
    
        // ✅ Save new Station Master user
        newUser.setRole(Role.STATION_MASTER);
        userRepository.save(newUser);
    
        // ✅ Send email to the new Station Master
        emailService.sendStationMasterCredentials(newUser.getEmail(), newUser.getPassword());
    
        System.out.println("✅ Station Master Created & Email Sent: " + newUser.getEmail());
    
        response.put("message", "Station Master created successfully. Credentials sent via email.");
        return ResponseEntity.ok(response);
    }




    @GetMapping("/draftsmen")
public ResponseEntity<List<User>> getAllDraftsmen() {
    List<User> draftsmen = userRepository.findByRole(Role.DRAFTSMAN);
    return ResponseEntity.ok(draftsmen);
}

    
    
}
