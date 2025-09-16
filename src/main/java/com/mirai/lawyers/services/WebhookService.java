package com.mirai.lawyers.services;

import com.mirai.lawyers.model.Contact;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    // URL de tu Google Apps Script Web App
    private final String APPS_SCRIPT_URL = "https://script.google.com/macros/s/YOUR_SCRIPT_ID/exec";

    public void sendToAppsScript(Contact contact) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Crear el payload para Apps Script
            WebhookPayload payload = new WebhookPayload();
            payload.setName(contact.getName());
            payload.setEmail(contact.getEmail());
            payload.setPhone(contact.getPhone());
            payload.setService(contact.getService());
            payload.setMessage(contact.getMessage());
            payload.setCreatedAt(contact.getCreatedAt().toString());

            HttpEntity<WebhookPayload> request = new HttpEntity<>(payload, headers);
            
            // Enviar POST a Apps Script
            restTemplate.postForEntity(APPS_SCRIPT_URL, request, String.class);
            
        } catch (Exception e) {
            System.err.println("Error enviando webhook: " + e.getMessage());
        }
    }

    // Clase interna para el payload
    public static class WebhookPayload {
        private String name;
        private String email;
        private String phone;
        private String service;
        private String message;
        private String createdAt;

        // Getters y Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        
        public String getService() { return service; }
        public void setService(String service) { this.service = service; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    }
}