package com.pavilion.service.impl;

import com.pavilion.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void Send(String sendTo, String subject, String content) {
        try
        {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("pavilionadmin@126.com");
            message.setTo(sendTo);
            message.setSubject(subject);
            message.setText(content);
            this.mailSender.send(mimeMessage);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
