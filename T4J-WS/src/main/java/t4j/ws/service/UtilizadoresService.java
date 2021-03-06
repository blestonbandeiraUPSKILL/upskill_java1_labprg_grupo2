package t4j.ws.service;

import t4j.ws.domain.Email;
import t4j.ws.domain.Rolename;
import t4j.ws.domain.Utilizador;
import t4j.ws.dto.ListaRolenamesDTO;
import t4j.ws.dto.Mapper;
import t4j.ws.dto.RolenameDTO;
import t4j.ws.dto.UtilizadorDTO;
import t4j.ws.exception.ConversaoException;
import t4j.ws.exception.RolenameAssociationException;
import t4j.ws.exception.UtilizadorInvalidoException;
import t4j.ws.persistence.RepositorioRolename;
import t4j.ws.persistence.RepositorioUtilizador;

import java.sql.SQLException;
import java.util.List;

public class UtilizadoresService {

    private RepositorioRolename repositorioRolename = Repo

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

    public static RolenameDTO getUserRolenames(String email) {
        RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();

        Utilizador utilizador = repositorioUtilizador.findByEmail(email);

        Rolename rolename = utilizador.getRolename();

        RolenameDTO rolenameDTO = Mapper.rolename2RolenameDTO(rolename);

        if(rolenameDTO != null) {
            return rolenameDTO;
        }
        else{
            throw new ConversaoException("RolenameDTO");
        }
    }

    public static boolean addRolenameToUser(String email, String rolename) throws SQLException {
        RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();

        Utilizador utilizador = repositorioUtilizador.findByEmail(email);

        if (utilizador == null) {
            throw new UtilizadorInvalidoException("Utilizador não encontrado");
        }

        RepositorioRolename repositorioRolename = RepositorioRolename.getInstance();

        Rolename rn = repositorioRolename.getByName(rolename);
        utilizador.setRolename(rn);

        if(rn == null) {
            throw new RolenameAssociationException("Ocorreu um erro ao adicionar o rolename ao utilizador: " + email);
        }

        return repositorioUtilizador.addRoleToUser(utilizador, rolename);
    }

    public static void createUserRole(RolenameDTO rolenameDTO) throws SQLException {
        RepositorioRolename repositorioRolename = RepositorioRolename.getInstance();

        Rolename rolename = Mapper.rolenameDTO2Rolename(rolenameDTO);

        List<Rolename> rolenames = repositorioRolename.getAll();

        if (!rolenames.contains(rolename)) {
            repositorioRolename.save(rolename);
        }
        else {
            throw new RolenameAssociationException("Ocorreu um erro ao criar o Rolename: " + rolename.getDesignacao());
        }

    }

    public static void deleteUserRole(String rolename) {
        RepositorioRolename
    }


}
