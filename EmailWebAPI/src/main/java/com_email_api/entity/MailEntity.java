package com_email_api.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class MailEntity {
    private String to;
    private String subject;
    private String message;
    private File attachment;

    public MailEntity(String to, String subject, String message,File attachment) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.attachment = attachment;
    }

    public MailEntity() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "MailEntity{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}
