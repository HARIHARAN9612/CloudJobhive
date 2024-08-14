package com.example.demo.service;

// package com.example.demo.service;

// import com.example.demo.model.Resume;
// import com.example.demo.repository.ResumeRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;

// @Service
// public class ResumeService {

//     @Autowired
//     private ResumeRepository resumeRepository;

//     public Resume saveResume(String name, MultipartFile file) throws IOException {
//         if (file.isEmpty()) {
//             throw new IOException("Uploaded file is empty");
//         }

//         Resume resume = new Resume();
//         resume.setName(name);
//         resume.setData(file.getBytes());
//         return resumeRepository.save(resume);
//     }
//     public byte[] getFile(Long id) {
//         Resume resume = resumeRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("File not found"));
//         return resume.getFile();
//     }
// }


import com.example.demo.model.Resume;
import com.example.demo.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume saveResume(String name, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Uploaded file is empty");
        }

        Resume resume = new Resume();
        resume.setName(name);
        resume.setData(file.getBytes());
        return resumeRepository.save(resume);
    }
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
    public byte[] getFile(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        return resume.getData();
    }
}