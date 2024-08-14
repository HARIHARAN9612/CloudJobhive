package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}