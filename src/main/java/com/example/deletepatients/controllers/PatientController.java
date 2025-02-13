package com.example.deletepatients.controllers;

import com.example.deletepatients.entity.Patient;
import com.example.deletepatients.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/delete-patient/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable Long id,
            @RequestParam String password
    ) {
        boolean success = patientService.deletePatient(id, password);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Healthy");
    }
}