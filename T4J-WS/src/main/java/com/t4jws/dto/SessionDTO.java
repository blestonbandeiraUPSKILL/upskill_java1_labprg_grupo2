package com.t4jws.dto;

public class SessionDTO {

    private UtilizadorDTO utilizadorDTO;
    private String loginTime;

    public SessionDTO() {

    }

    public UtilizadorDTO getUtilizadorDTO() {
        return utilizadorDTO;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setUtilizadorDTO(UtilizadorDTO utilizadorDTO) {
        this.utilizadorDTO = utilizadorDTO;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
