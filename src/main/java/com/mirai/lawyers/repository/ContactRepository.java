package com.mirai.lawyers.repository;

import com.mirai.lawyers.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}