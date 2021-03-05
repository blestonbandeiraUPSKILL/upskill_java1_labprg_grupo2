package t4j.ws.dto;

import t4j.ws.domain.Password;

public class UtilizadorDTO {

    private String username;
    private String email;
    private Password password;
    private String rolename;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public String getRolename() {
        return rolename;
    }
}
