package com.evozon.service;

import com.evozon.domain.User;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by iuliacodau on 25/07/2016.
 */
public class SendOrderMail {

    private MailSender mailSender;
    private SimpleMailMessage placementMessage;
    private String url;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setPlacementMessage(SimpleMailMessage placementMessage) { this.placementMessage = placementMessage; }

    public void setUrl(String url) {
        this.url = url;
    }

    public void sendOrderPlacementMail(String email, String name, String keyUrl, String path) {
        SimpleMailMessage msg = new SimpleMailMessage(this.placementMessage);
        msg.setTo(email);
        msg.setText("Hello beautiful customer!\n" + "Thank you for buying from us.\n If you want to see the details of your orders, please follow the link: "+ url + path + "?orderKey="+ keyUrl);
        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
