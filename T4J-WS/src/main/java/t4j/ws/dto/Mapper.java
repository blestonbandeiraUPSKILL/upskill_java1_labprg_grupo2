package t4j.ws.dto;

import t4j.ws.domain.Sessao;
import t4j.ws.domain.Utilizador;

public class Mapper {

    public static UtilizadorDTO utilizador2UtilizadorDTO(Utilizador utilizador) {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO();
        utilizadorDTO.setUsername(utilizador.getUsername());
        utilizadorDTO.setEmail(utilizador.getEmail().toString());
        utilizadorDTO.setPassword(utilizador.getPassword());
        utilizadorDTO.setRolename(utilizador.getRolename());

        return utilizadorDTO;
    }

    public static Utilizador utilizadorDTO2Utilizador(UtilizadorDTO utilizadorDTO) {
        Utilizador utilizador = new Utilizador();
        utilizador.setUsername(utilizadorDTO.getUsername());
        utilizador.setEmail(utilizadorDTO.getEmail());
        utilizador.setPassword(utilizadorDTO.getPassword());
        utilizador.setRolename(utilizadorDTO.getRolename());

        return utilizador;
    }

    public static ContextoDTO contexto2ContextoDTO(Contexto contexto) {
        ContextoDTO contextoDTO = new ContextoDTO();
        contextoDTO.setAppContext(contexto.getContexto());

        return contextoDTO;
    }

    public static SessaoDTO sessao2SessaoDTO(Sessao sessao) {
        SessaoDTO sessaoDTO = new SessaoDTO();
        UtilizadorDTO utilizadorDTO = Mapper.utilizador2UtilizadorDTO(sessao.getUtilizador());

        sessaoDTO.setUtilizadorDTO(utilizadorDTO);
        sessaoDTO.setTimeStamp(sessaoDTO.getTimeStamp());

        return sessaoDTO;

    }
}
