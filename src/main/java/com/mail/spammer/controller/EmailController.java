package com.mail.spammer.controller;

import com.mail.spammer.services.MailGunEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final MailGunEmailService mailGunEmailService;


    @PostMapping
    public ResponseEntity<Void> sendEmai(){
        return null;
    }


}
