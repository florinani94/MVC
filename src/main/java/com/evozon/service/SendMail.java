package com.evozon.service;

import com.evozon.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by dianamohanu on 19/07/2016.
 */
public class SendMail
{
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private String url;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void sendConfirmationMail(User user, String path) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(user.getEmail());
        msg.setText(
                "Hello " + user.getUsername() + ", please confirm your account by clicking on the following link: " + url + path + "?key=" + user.getKeyUrl() );
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}