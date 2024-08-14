package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Job;
import com.example.demo.repository.JobPostRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;

    public Job saveJobPost(Job job) {
        return jobPostRepository.save(job);
    }

    public List<Job> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    public Job getJobPostById(Long id) {
        return jobPostRepository.findById(id).orElse(null);
    }

    public void deleteJobPost(Long id) {
        jobPostRepository.deleteById(id);
    }
  
       public List<Job> getFilteredJobPosts(Map<String, Boolean> filters) {
        // Extract filter criteria
        List<String> jobTypes = filters.entrySet().stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        // Query the database based on filters
        return jobPostRepository.findByJobTypeIn(jobTypes);
    }
}
