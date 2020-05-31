package com.bridgelabz.bookstore.dto;

import java.io.Serializable;

public class RabbitMqDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String to;
    private String from;
    private String subject;
    private String body;

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
