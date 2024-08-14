package com.example.demo.controller;

// package com.example.demo.controller;

// import com.example.demo.model.Resume;
// import com.example.demo.service.ResumeService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.MediaType;


// import java.io.IOException;

// @RestController
// @RequestMapping("/api/resumes")
// @CrossOrigin(origins = "http://localhost:3000")
// public class ResumeController {

//     private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

//     @Autowired
//     private ResumeService resumeService;

//     @GetMapping("/hello")
//     public String hello(){
//         return "hello";
//     }

//     @PostMapping("/upload")
//     public Resume uploadResume(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
//         logger.info("Received upload request for name: {} with file: {}", name, file.getOriginalFilename());

//         if (file.isEmpty()) {
//             logger.error("File is empty");
//             throw new IOException("Uploaded file is empty");
//         }

//         try {
//             Resume savedResume = resumeService.saveResume(name, file);
//             logger.info("File uploaded successfully: {}", savedResume.getId());
//             return savedResume;
//         } catch (Exception e) {
//             logger.error("Error uploading resume", e);
//             throw e;  // Re-throw the exception to trigger the 500 error response
//         }
//     }
//         @GetMapping("/{id}")
//     public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
//         byte[] file = resumeService.getFile(id);
//         return ResponseEntity.ok()
//                 .contentType(MediaType.APPLICATION_PDF) // Adjust content type if needed
//                 .body(file);
//     }
// }



import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "http://localhost:3000")
public class ResumeController {

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/all")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeService.getAllResumes();
        return ResponseEntity.ok(resumes);
    }
    @PostMapping("/upload")
    public Resume uploadResume(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Received upload request for name: {} with file: {}", name, file.getOriginalFilename());

        if (file.isEmpty()) {
            logger.error("File is empty");
            throw new IOException("Uploaded file is empty");
        }

        try {
            Resume savedResume = resumeService.saveResume(name, file);
            logger.info("File uploaded successfully: {}", savedResume.getId());
            return savedResume;
        } catch (Exception e) {
            logger.error("Error uploading resume", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        byte[] file = resumeService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }
}