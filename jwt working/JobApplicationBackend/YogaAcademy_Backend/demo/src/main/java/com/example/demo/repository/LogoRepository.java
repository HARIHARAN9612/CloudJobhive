package com.example.demo.repository;


import com.example.demo.model.Logo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoRepository extends JpaRepository<Logo, Long> {
    // Custom query methods can be added here if needed
}

