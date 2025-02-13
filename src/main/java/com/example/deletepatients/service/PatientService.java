package com.example.deletepatients.service;

import com.example.deletepatients.entity.*;
import com.example.deletepatients.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Value("${password.verify.url}")
    private String PASSWORD_VERIFY_URL;


    public boolean deletePatient(Long id, String password) {
        return patientRepository.findById(id)
                .map(patient -> {

                    boolean passwordMatches = verifyPassword(password, patient.getPasswordHash());
                    if (passwordMatches) {
                        patientRepository.deleteById(id);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    private boolean verifyPassword(String password, String hash) {
        RestTemplate restTemplate = new RestTemplate();

        VerifyRequest verifyRequest = new VerifyRequest(password, hash);

        try {
            HttpEntity<VerifyRequest> request = new HttpEntity<>(verifyRequest);
            ResponseEntity<VerifyResponse> response = restTemplate.postForEntity(PASSWORD_VERIFY_URL, request, VerifyResponse.class);

            return response.getBody() != null && response.getBody().isMatch();
        } catch (Exception e) {

            e.printStackTrace();
            return password.equals(hash);
        }
    }


}