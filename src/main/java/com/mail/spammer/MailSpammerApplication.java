package com.mail.spammer;

import com.mail.spammer.services.MailGunEmailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class MailSpammerApplication implements CommandLineRunner {

    private static class MyTimeTask extends TimerTask {

        public void run() {

        }
    }

    @Autowired
    ServletContext servletContext;


    @Autowired
    MailGunEmailService mailGunEmailService;

    public static void main(String[] args) {
        SpringApplication.run(MailSpammerApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws IOException, URISyntaxException, ParseException, UnirestException {
        long delay = ChronoUnit.MILLIS.between(LocalTime.now(), LocalTime.of(14, 38, 0));
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.schedule(() -> (mailGunEmailService.sendHTML("jasiujanow9@gmail.com", "jasiujanow99@gmail.com", "Hello World", "Hello, <strong>how are you doing?</strong>"), delay, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> {
                    try {
                        mailGunEmailService.sendHTML("jasiujanow99@gmail.com", "test",
                                "Hello World", null);
                    } catch (IOException | UnirestException e) {
                        e.printStackTrace();
                    }
                },
                delay, TimeUnit.MILLISECONDS);
        //        sendInlineImage();

    }

}

