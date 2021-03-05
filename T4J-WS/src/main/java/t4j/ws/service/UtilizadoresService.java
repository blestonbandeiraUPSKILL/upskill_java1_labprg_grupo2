package t4j.ws.service;

import t4j.ws.domain.Email;
import t4j.ws.domain.Rolename;
import t4j.ws.domain.Utilizador;
import t4j.ws.dto.ListaRolenamesDTO;
import t4j.ws.dto.Mapper;
import t4j.ws.dto.UtilizadorDTO;
import t4j.ws.exception.ConversaoException;
import t4j.ws.persistence.RepositorioRolename;
import t4j.ws.persistence.RepositorioUtilizador;

import java.sql.SQLException;
import java.util.List;

public class UtilizadoresService {

    public static UtilizadorDTO getUtilizador(Email email) {

        RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();

        Utilizador utilizador = repositorioUtilizador.getByEmail(email.toString());

        if(utilizador == null) {
            return null;
        }

        UtilizadorDTO utilizadorDTO = Mapper.utilizador2UtilizadorDTO(utilizador);
        if(utilizadorDTO != null) {
            return utilizadorDTO;
        }
        else {
            throw new ConversaoException("UtilizadorDTO");
        }
    }

    public static void registerUser(UtilizadorDTO utilizadorDTO) {

        Utilizador utilizador = Mapper.utilizadorDTO2Utilizador(utilizadorDTO);

        if(utilizador != null) {
            RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();
            repositorioUtilizador.save(utilizador);
        }
        else {
            throw new ConversaoException("UtilizadorDTO");
        }
    }

    public static void registerUserWithRoles(UtilizadorDTO utilizadorDTO) {

        Utilizador utilizador = Mapper.utilizadorDTO2Utilizador(utilizadorDTO);

        if (utilizador != null) {
            RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();
            repositorioUtilizador.save(utilizador);
        }
        else {
            throw new ConversaoException("UtilizadorDTO");
        }
    }

    public static ListaRolenamesDTO getRoles() throws SQLException {
        ListaRolenamesDTO listaRolenamesDTO = null;

        RepositorioRolename repositorioRolename = RepositorioRolename.getInstance();
        List<Rolename> rolenames = repositorioRolename.getAll();

        listaRolenamesDTO = Mapper.listaRolenames2ListaRolenamesDTO(rolenames);

        return listaRolenamesDTO;
    }


}
