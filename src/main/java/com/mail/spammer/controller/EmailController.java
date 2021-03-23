package com.mail.spammer.controller;

import com.mail.spammer.services.MailGunEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

@RestController
@RequestMapping("api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final MailGunEmailService mailGunEmailService;


    @PostMapping
    @CrossOrigin
    public ResponseEntity<Void> sendEmai(@RequestPart("file") MultipartFile file,
                                         @RequestPart("to") String to,@RequestPart("subject") String subject,
                                         @RequestPart("content") String content,
                                         @RequestPart("sentAt") String sentAt

    ) throws IOException {
        var hours = Integer.valueOf(sentAt.substring(0,2));
        var minutes = Integer.valueOf(sentAt.substring(2,4));
        var seconds = Integer.valueOf(sentAt.substring(4,6));
        var sentEmailAt = LocalTime.of(hours,minutes,seconds);
        File tempFile = File.createTempFile("wsb-spammer-mail", "-test");
        file.transferTo(tempFile);
        tempFile.deleteOnExit();
        mailGunEmailService.sendHTML(to,subject,content,sentEmailAt,  tempFile);
        return ResponseEntity.noContent().build();
    }


}
