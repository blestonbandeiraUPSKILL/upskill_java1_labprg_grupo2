package t4j.ws.service;

import t4j.ws.domain.Email;
import t4j.ws.domain.Rolename;
import t4j.ws.domain.Utilizador;
import t4j.ws.dto.ListaRolenamesDTO;
import t4j.ws.dto.Mapper;
import t4j.ws.dto.RolenameDTO;
import t4j.ws.dto.UtilizadorDTO;
import t4j.ws.exception.RolenameAssociationException;
import t4j.ws.exception.UtilizadorInvalidoException;
import t4j.ws.persistence.RepositorioRolename;
import t4j.ws.persistence.RepositorioUtilizador;

import java.sql.SQLException;
import java.util.List;

public class UtilizadoresService {

    private static RepositorioRolename repositorioRolename = RepositorioRolename.getInstance();
    private static RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();

    public static UtilizadorDTO getUtilizador(Email email) {

        Utilizador utilizador = repositorioUtilizador.getByEmail(email.toString());

        if(utilizador == null) {
            return null;
        }

        UtilizadorDTO utilizadorDTO = Mapper.utilizador2UtilizadorDTO(utilizador);
        return utilizadorDTO;
    }

    public static void registerUser(UtilizadorDTO utilizadorDTO) {

        Utilizador utilizador = Mapper.utilizadorDTO2Utilizador(utilizadorDTO);

        RepositorioUtilizador repositorioUtilizador = RepositorioUtilizador.getInstance();
        repositorioUtilizador.save(utilizador);
    }

    public static void registerUserWithRoles(UtilizadorDTO utilizadorDTO, Rolename rolename) throws SQLException {

        Utilizador utilizador = Mapper.utilizadorDTO2Utilizador(utilizadorDTO);
        Rolename rn = repositorioRolename.getByName(rolename.getDesignacao());
        utilizador.setRolename(rn);
        repositorioUtilizador.saveWithRole(utilizador);

    }

    public static ListaRolenamesDTO getRoles() throws SQLException {
        List<Rolename> rolenames = repositorioRolename.getAll();

        return Mapper.listaRolenames2ListaRolenamesDTO(rolenames);
    }

    public static RolenameDTO getUserRolenames(String email) {
        Utilizador utilizador = repositorioUtilizador.findByEmail(email);

        Rolename rolename = utilizador.getRolename();

        RolenameDTO rolenameDTO = Mapper.rolename2RolenameDTO(rolename);

        return rolenameDTO;
    }

    public static boolean addRolenameToUser(String email, String rolename) throws SQLException {
        Utilizador utilizador = repositorioUtilizador.findByEmail(email);

        if (utilizador == null) {
            throw new UtilizadorInvalidoException("Utilizador não encontrado");
        }

        Rolename rn = repositorioRolename.getByName(rolename);
        utilizador.setRolename(rn);

        if(rn == null) {
            throw new RolenameAssociationException("Ocorreu um erro ao adicionar o rolename ao utilizador: " + email);
        }

        return repositorioUtilizador.addRoleToUser(utilizador, rolename);
    }

    public static void createUserRole(RolenameDTO rolenameDTO) throws SQLException {
        Rolename rolename = Mapper.rolenameDTO2Rolename(rolenameDTO);

        List<Rolename> rolenames = repositorioRolename.getAll();

        if (!rolenames.contains(rolename)) {
            repositorioRolename.save(rolename);
        }
        else {
            throw new RolenameAssociationException("Ocorreu um erro ao criar o Rolename: " + rolename.getDesignacao());
        }

    }

    public static boolean deleteUserRole(String rolename) throws Exception {
        List<Rolename> rolenames = repositorioRolename.getAll();

        Rolename rn = repositorioRolename.getByName(rolename);

        if(rolenames.contains(rolename)) {
            repositorioRolename.deleteRolename(rolename);
            return true;
        }
        else {
            throw new Exception("Erro ao apagar o Rolename");
        }
    }

    public static boolean deleteRoleFromUser(String email, String rolename) throws Exception {
        Utilizador utilizador = repositorioUtilizador.getByEmail(email);

        Rolename rn = repositorioRolename.getByName(rolename);

        if(utilizador.getRolename().equals(rolename)) {
            repositorioUtilizador.deleteRoleFromUser(utilizador);
            return true;
        }
        else {
            throw new Exception("Ocorreu um erro ao apagar o Rolename do utilizador: " + email);
        }
    }


}
