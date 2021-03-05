package com.t4jws.dto;

public class LoginDTO {

    private EmailDTO emailDTO;

    private PasswordDTO passwordDTO;

    public LoginDTO() {

    }

    public EmailDTO getEmailDTO() {
        return emailDTO;
    }

    public PasswordDTO getPasswordDTO() {
        return passwordDTO;
    }

    public void setEmailDTO(EmailDTO emailDTO) {
        this.emailDTO = emailDTO;
    }

    public void setPasswordDTO(PasswordDTO passwordDTO) {
        this.passwordDTO = passwordDTO;
    }
}
