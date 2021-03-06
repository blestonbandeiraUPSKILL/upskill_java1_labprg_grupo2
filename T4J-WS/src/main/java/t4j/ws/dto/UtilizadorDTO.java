package t4j.ws.dto;

import t4j.ws.domain.Password;
import t4j.ws.domain.Rolename;

public class UtilizadorDTO {

    private String username;
    private String email;
    private Password password;
    private Rolename rolename;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setRolename(Rolename rolename) {
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

    public Rolename getRolename() {
        return rolename;
    }
}
