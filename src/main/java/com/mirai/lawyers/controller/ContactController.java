package com.mirai.lawyers.controller;

import com.mirai.lawyers.dto.ContactRequest;
import com.mirai.lawyers.model.Contact;
import com.mirai.lawyers.repository.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping
    public Contact createContact(@RequestBody ContactRequest request) {
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setMessage(request.getMessage());
        contact.setService(request.getService());
        return contactRepository.save(contact);
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }
}