package com.mirai.lawyers.dto;

import lombok.Data;

@Data
public class ContactRequest {
    private String name;
    private String email;
    private String phone;
    private String service;
    private String message;
}