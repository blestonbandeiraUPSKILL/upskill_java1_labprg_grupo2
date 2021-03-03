package com.t4jws.service;

import com.t4jws.dto.UtilizadorDTO;
import com.t4jws.exception.ConversaoException;
import com.t4jws.model.Email;
import com.t4jws.model.Password;
import com.t4jws.model.Utilizador;
import com.t4jws.repository.RepositorioUtilizadorDatabase;
import org.apache.catalina.mapper.Mapper;

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

    public static void registerUser(Email email, String username, Password password) {
        Utilizador utilizador = new Utilizador(email.toString(), username, password.toString());

        if(utilizador != null) {
            repositorioUtilizadorDatabase.save(utilizador);
        }

        else {
            throw new ConversaoException("UtilizadorDTO");
        }
    }

    public static void registerUserWithRoles(Email email, String username, Password password, String rolename) {
        Utilizador utilizador = new Utilizador(email, username, password, rolename);

    }
}
