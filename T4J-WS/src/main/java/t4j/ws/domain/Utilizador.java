/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t4j.ws.domain;


import t4j.ws.exception.NomeInvalidoException;

import java.io.Serializable;

/**
 *
 * @author CAD
 */

public class Utilizador implements Serializable{
   

    private String username;
    private Email email;
    private Password password;
    private Rolename rolename;

    public Utilizador(){
    }

    public Utilizador(Email emailUt, String username, Password password){
        this.email = emailUt;
        setUsername(username);
        setPassword(password);
    }

    public Utilizador(Utilizador utilizador) {
        this.email = utilizador.getEmail();
        this.username = utilizador.getUsername();
        this.password = utilizador.getPassword();
        this.rolename = utilizador.getRolename();
    }

    public Utilizador(String emailUtilizador, String username, String password, Rolename rolename) {
        setEmail(new Email(emailUtilizador));
        setUsername(username);
        setPassword(new Password(password));
        setRolename(new Rolename(rolename));

    }

    public Utilizador(String email, String username) {
        setUsername(username);
        setEmail(new Email(email));
    }

    public void setUsername(String username){
        if (username == null || username.trim().isEmpty()) {
            throw new NomeInvalidoException("username é inválido!");
        }
        this.username = username;
    }

    public void setEmail(Email email){
        this.email = new Email(email);
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public void setPassword(Password password){
        this.password = password;
    }

    public void setRolename(Rolename rolename){
        this.rolename = rolename;
    }

    public String getUsername(){
        return username;
    }

    public Email getEmail(){
        return email;
    }  

    public Password getPassword(){
        return password;
    } 

    public Rolename getRolename(){
        return rolename;
    }

    @Override
    public String toString(){
        return String.format("username: %-20s |Email:%-20s",
                username, email.toString());
    }

    public String toStringComPass(){
        return String.format("username: %s %nEmail:%s %nPassword: %s "
                + "%nRolename: %s", username, email.toString(),
                password.getPasswordText(), rolename.toString());
    }

    public String toStringSemPass(){
        return String.format("username: %s %nEmail:%s %nRolename: %s",
                username, email.toString(), rolename);
    }


}
