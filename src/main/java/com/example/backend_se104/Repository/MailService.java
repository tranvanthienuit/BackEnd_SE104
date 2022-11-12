package com.example.backend_se104.Repository;


import com.example.backend_se104.Entity.Mail;

public interface MailService {
    public void sendEmail(Mail mail);

    public boolean checkMail(String mail);
}
