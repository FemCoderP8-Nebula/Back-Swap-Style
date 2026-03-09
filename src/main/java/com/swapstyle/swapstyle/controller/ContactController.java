package com.swapstyle.swapstyle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swapstyle.swapstyle.service.ContactEmailService;
import com.swapstyle.swapstyle.dto.request.ContactRequestDTO;

@RestController
@RequestMapping("api/v1/contact")
public class ContactController {

    private final ContactEmailService emailService;

    public ContactController(ContactEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendContact(@RequestBody ContactRequestDTO dto) {
        emailService.sendContactEmail(dto);
        return ResponseEntity.ok().build();
    }
}
