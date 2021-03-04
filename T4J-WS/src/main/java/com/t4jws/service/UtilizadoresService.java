package com.t4jws.service;

import com.t4jws.dto.ListaUtilizadoresDTO;
import com.t4jws.dto.UtilizadorDTO;
import com.t4jws.exception.ConversaoException;
import com.t4jws.model.Email;
import com.t4jws.model.Password;
import com.t4jws.model.Utilizador;
import com.t4jws.repository.RepositorioUtilizadorDatabase;
import org.apache.catalina.mapper.Mapper;

import java.util.List;

public class UtilizadoresService {

    private static RepositorioUtilizadorDatabase repositorioUtilizadorDatabase = RepositorioUtilizadorDatabase.getInstance();


    public static UtilizadorDTO getUtilizador(Email email) {
        Utilizador utilizador = repositorioUtilizadorDatabase.getUtilizadorByEmail(email);

        if(utilizador == null) {
            return null;
        }

        UtilizadorDTO utilizadorDTO = Mapper.utilizador2UtilizadorDTO(utilizador);

        if(utilizadorDTO != null) {
            return utilizadorDTO;
        }
        else  {
            throw new ConversaoException("UtilizadorDTO");
        }
    }

    public static List<UtilizadorDTO> getUtilizadores() {
        List<Utilizador> utilizadores = repositorioUtilizadorDatabase.getAll();

        if(utilizadores == null) {
            return null;
        }

        ListaUtilizadoresDTO utilizadoresDTO = Mapper.listaUtilizadores2ListaUtilizadoresDTO(utilizadores);

        if(utilizadoresDTO != null) {
            return utilizadoresDTO;
        }
        else {
            throw new ConversaoException("ListaUtilizadoresDTO");
        }
    }

    public static void registerUser(Email email, String username, Password password) {
        Utilizador utilizador = new Utilizador(email, username, password);

        if(utilizador != null) {
            repositorioUtilizadorDatabase.save(utilizador);
        }

        else {
            throw new ConversaoException("UtilizadorDTO");
        }
    }

    public static void registerUserWithRoles(Email email, String username, Password password, String rolename) {
        Utilizador utilizador = new Utilizador(email.getEmailText(), username, password.getPasswordText(), rolename);

        if (utilizador != null) {
            repositorioUtilizadorDatabase.save(utilizador);
        }
        else {
            throw new ConversaoException("UtilizadorDTO");
        }



    }

    public static boolean login(String appContext, Email email, Password password){
        return false;
    }

    public static boolean logout(String appContext) {
        return false;
    }

    public static void createUserRoles(String appContext, Email email, String rolename) {

    }

    public static void updateUserRoles(String appContext, Email email, String rolename) {

    }

    public static void deleteUserRoles(String appContext, Email email, String rolename) {

    }

    public static void createRoles(String appContext, String rolename, String description) {

    }

    public static void updateRoles(String appContext, String rolename, String description) {

    }

    public static void deleteRoles(String appContext, String rolename, String description) {

    }

    public static void getSession(String appContext) {

    }

    public static String getContext(String appKey) {
        return null;
    }
}
