package com.mirai.lawyers.controller;

import com.mirai.lawyers.dto.ContactRequest;
import com.mirai.lawyers.model.Contact;
import com.mirai.lawyers.repository.ContactRepository;
import com.mirai.lawyers.services.WebhookService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

  private final ContactRepository contactRepository;
    private final WebhookService webhookService;

    public ContactController(ContactRepository contactRepository, WebhookService webhookService) {
        this.contactRepository = contactRepository;
        this.webhookService = webhookService;
    }

    @PostMapping
    public Contact createContact(@RequestBody ContactRequest request) {
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setMessage(request.getMessage());
        contact.setService(request.getService());
        
        // Guardar en BD
        Contact savedContact = contactRepository.save(contact);
        
        // Enviar webhook a Apps Script
        webhookService.sendToAppsScript(savedContact);
        
        return savedContact;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }
}