package com.pavilion.service;

public interface MailService {
    void Send(String sendTo, String subject,String content);
}
