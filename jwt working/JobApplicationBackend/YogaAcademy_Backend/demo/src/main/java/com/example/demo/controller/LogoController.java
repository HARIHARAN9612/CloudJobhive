package com.example.demo.controller;


import com.example.demo.model.Logo;
import com.example.demo.service.LogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logos")
public class LogoController {

    @Autowired
    private LogoService logoService;

    @GetMapping
    public List<Logo> getAllLogos() {
        return logoService.getAllLogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logo> getLogoById(@PathVariable Long id) {
        Optional<Logo> logo = logoService.getLogoById(id);
        return logo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Logo> createLogo(@RequestBody Logo logo) {
        Logo savedLogo = logoService.saveLogo(logo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Logo> updateLogo(@PathVariable Long id, @RequestBody Logo logo) {
        if (!logoService.getLogoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        logo.setId(id);
        Logo updatedLogo = logoService.saveLogo(logo);
        return ResponseEntity.ok(updatedLogo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogo(@PathVariable Long id) {
        if (!logoService.getLogoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        logoService.deleteLogo(id);
        return ResponseEntity.noContent().build();
    }
}
