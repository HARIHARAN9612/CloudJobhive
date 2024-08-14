package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Job;

public interface JobPostRepository extends JpaRepository<Job, Long> {
    List<Job> findByJobTypeIn(List<String> jobTypes);}