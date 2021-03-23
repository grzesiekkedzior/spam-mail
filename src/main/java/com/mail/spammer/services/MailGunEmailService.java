package com.mail.spammer.services;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
public class MailGunEmailService  {

    private String password;
    private String messagesUrl;
    private String username;

    @Autowired
    ServletContext servletContext;

    private SpringRestClient springRestClient;

    @Autowired
    public MailGunEmailService(SpringRestClient springRestClient,String mailGunAPIMessagesUrl, String mailGunAPIUsername,
                               String mailGunAPIPassword) {
        this.springRestClient=springRestClient;
        this.username = mailGunAPIUsername;
        this.password = mailGunAPIPassword;
        this.messagesUrl = mailGunAPIMessagesUrl;
    }


    public void sendHTML(String to, String subject, String content,LocalTime sentAt, File file ) {
        long delay = ChronoUnit.MILLIS.between(LocalTime.now(),sentAt);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
        scheduler.schedule(() -> {
                    try {
                        sendInlineImage(to,subject,content, file);

                    } catch (IOException | UnirestException e) {
                        e.printStackTrace();
                    }
                },
                delay, TimeUnit.MILLISECONDS);
    }

    public   HttpResponse<String> sendInlineImage(String to, String subject, String content,File file) throws UnirestException, IOException {
        var fileName = file.getName();

        HttpResponse<String> response = Unirest.post(messagesUrl)
                .basicAuth(username, password)
                .field("from", "WSB project jasiujanow9@gmail.com")
                .field("to", to)
                .field("subject", subject)
                .field("text", "Testing out some Mailgun awesomeness!")
                .field("html", "<html><div>"+ content+ "</div> <img src=\"cid:"+fileName+"\"></html>")
                .field("inline", file)
                .asString();

        return response;
    }

}

//
