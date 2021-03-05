package com.grupo2.t4j.domain;

public interface IEmail {

    public void setTo(String email);
    public void setSubject(String subject);
    public void setText(String text);
    public void send();
}
