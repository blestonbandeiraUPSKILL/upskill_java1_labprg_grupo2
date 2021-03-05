package t4j.ws.service;

import t4j.ws.domain.Sessao;
import t4j.ws.domain.Utilizador;
import t4j.ws.dto.*;
import t4j.ws.exception.ConversaoException;
import t4j.ws.exception.KeyInvalidaException;
import t4j.ws.persistence.RepositorioSessao;
import t4j.ws.persistence.RepositorioUtilizador;

import java.sql.SQLException;

public class GestaoUtilizadoresService {

    public static ContextoDTO generateContext(String appKey) {
        Contexto contexto = null;

        try {
            contexto = new Contexto(appKey);
        }
        catch (KeyInvalidaException exception) {
            exception.printStackTrace();
            exception.getMessage();
        }

        ContextoDTO contextoDTO = Mapper.contexto2ContextoDTO(contexto);

        if(contextoDTO != null) {
            RepositorioSessao repositorioSessao = RepositorioSessao.getInstance();
            repositorioSessao.saveContext(contexto);
            return contextoDTO;
        }
        else {
            throw new ConversaoException("ContextoDTO");
        }
    }

    public static boolean validateContexto(ContextoDTO contextoDTO) {
        Contexto contexto = RepositorioSessao.getInstance().findContextByString(contextoDTO.getAppContext());

        return contexto.isValido();
    }

    public static boolean login(LoginDTO loginDTO, ContextoDTO contextoDTO) throws SQLException {
        Utilizador utilizador = RepositorioUtilizador.getInstance().findByEmail(loginDTO.getEmail().toString());

        if(utilizador == null || !(utilizador.getPassword().equals(loginDTO.getPassword()))) {
            return false;
        }

        Contexto contexto = RepositorioSessao.getInstance().findContextByString(contextoDTO.getAppContext());
        Sessao sessao = new Sessao(utilizador, contexto);
        SessaoDTO sessaoDTO = Mapper.sessao2SessaoDTO(sessao);

        if(sessao != null) {
            return RepositorioSessao.getInstance().saveSessao(sessao);
        }
        else {
            throw new ConversaoException("SessaoDTO");
        }
    }

    public static boolean logout(ContextoDTO contextoDTO) throws SQLException {
        return RepositorioSessao.getInstance().contextInvalid(contextoDTO.getAppContext());
    }

    public static SessaoDTO getSession(ContextoDTO contextoDTO) throws SQLException {
        Sessao sessao = RepositorioSessao.getInstance().findByContext(contextoDTO.getAppContext());

        if(sessao == null) {
            return null;
        }

        SessaoDTO sessaoDTO = Mapper.sessao2SessaoDTO(sessao);
        if(sessaoDTO != null) {
            return sessaoDTO;
        }
        else {
            throw new ConversaoException("SessaoDTO");
        }
    }
}
