package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Job;
import com.example.demo.service.JobPostService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    @PostMapping
    public ResponseEntity<Job> createJobPost(@RequestBody Job job) {
        Job savedJobPost = jobPostService.saveJobPost(job);
        return new ResponseEntity<>(savedJobPost, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Job> getAllJobPosts() {
        return jobPostService.getAllJobPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobPostById(@PathVariable Long id) {
        Job jobPost = jobPostService.getJobPostById(id);
        if (jobPost != null) {
            return new ResponseEntity<>(jobPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobPost(@PathVariable Long id, @RequestBody Job job) {
        Job existingJobPost = jobPostService.getJobPostById(id);
        if (existingJobPost != null) {
            job.setId(id); // Ensure the ID of the job is set
            Job updatedJobPost = jobPostService.saveJobPost(job); // Assuming saveJobPost method handles updates
            return new ResponseEntity<>(updatedJobPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        jobPostService.deleteJobPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Job>> getFilteredJobPosts(@RequestBody Map<String, Boolean> filters) {
        List<Job> filteredJobs = jobPostService.getFilteredJobPosts(filters);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }
}
