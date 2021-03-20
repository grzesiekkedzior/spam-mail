package com.mail.spammer.services;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;


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

    public void sendHTML(String to, String subject, String content, List<File> files) throws IOException, UnirestException {
        sendInlineImage(to,subject,content, files);
    }

    public JsonNode sendInlineImage(String to, String subject, String content, List<File> files) throws UnirestException, IOException {

        HttpResponse<JsonNode> response = Unirest.post(messagesUrl)
                .basicAuth(username, password)
                .field("from", "jasiujanow9@gmail.com")
                .field("to", to)
                .field("subject", subject)
                .field("text", "Testing out some Mailgun awesomeness!")
                .field("html", "<html><div>"+ content+ "</div> <img src=\"cid:test.png\"></html>")
                .field("inline", new File("D:\\IdeaProjects\\spam-mail\\src\\main\\resources\\test.png"))
                .asJson();

        return response.getBody();
    }

}

//
