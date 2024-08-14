package com.example.demo.service;

import com.example.demo.model.Application;
import com.example.demo.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public Application updateApplicationStatus(Long id, String status) {
        Optional<Application> applicationOpt = applicationRepository.findById(id);
        if (applicationOpt.isPresent()) {
            Application application = applicationOpt.get();
            application.setStatus(status);
            return applicationRepository.save(application);
        }
        return null;
    }
    
}
