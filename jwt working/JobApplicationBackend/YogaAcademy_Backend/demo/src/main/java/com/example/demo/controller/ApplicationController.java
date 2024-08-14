package com.example.demo.controller;

import com.example.demo.model.Application;
import com.example.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cdash/applications")
@CrossOrigin(origins = "http://localhost:3000") 
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Optional<Application> application = applicationService.getApplicationById(id);
        return application.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/api/jobapplications")  
    public ResponseEntity<Application> createApplication(
        @RequestParam("company") String company,
        @RequestParam("jobTitle") String jobTitle,
        @RequestParam("location") String location,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("contact") String contact,

            @RequestParam("experience") String experience,
            @RequestParam("address") String address,
            @RequestParam("city") String city,  
            @RequestParam("pincode") String pincode,  
            @RequestParam("date") LocalDate date,
            @RequestPart("cv") MultipartFile cvFile) {  
        try {
            Application application = new Application();
            application.setCompany(company);
            application.setLocation(location);
            application.setJobTitle(jobTitle);
            application.setFirstName(firstName);
            application.setLastName(lastName);
            application.setEmail(email);
            application.setContact(contact);

            application.setExperience(experience);
            application.setAddress(address);
            application.setCity(city);  
            application.setPincode(pincode);  
            application.setDate(date);
            application.setCv(cvFile.getBytes());
            application.setAccepted(false);
            application.setRejected(false);
            application.setStatus("pending");
    
            Application savedApplication = applicationService.saveApplication(application);
            return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application application) {
        if (!applicationService.getApplicationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        application.setId(id);
        Application updatedApplication = applicationService.saveApplication(application);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (!applicationService.getApplicationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        Application updatedApplication = applicationService.updateApplicationStatus(id, statusUpdate.get("status"));
        if (updatedApplication != null) {
            return ResponseEntity.ok(updatedApplication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
