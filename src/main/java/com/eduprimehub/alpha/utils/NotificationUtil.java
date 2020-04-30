package com.eduprimehub.alpha.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationUtil {

    @Autowired
    private JavaMailSender sender;

    public int sendEmail() throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        Map<String, Integer> statsMap = new HashMap<>();
        statsMap.put("abc", 1);
        statsMap.put("axbc", 1);
        helper.setFrom("aditya.chand@monsterindia.com");
        helper.setTo("aditya.chand@monsterindia.com");
        helper.setText(statsMap.toString());
        helper.setSubject("Hi");
        sender.send(message);
        return 0;
    }

    //TOdo: change the input parameter
    public int sendEmail(String plannerStatsObject) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("<html><table><tr></table></html>");

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("aditya.chand@monsterindia.com");
        helper.setTo("aditya.chand@monsterindia.com");
        helper.setText(plannerStatsObject);
        helper.setSubject("Hi");

        sender.send(message);
        return 0;
    }
}
