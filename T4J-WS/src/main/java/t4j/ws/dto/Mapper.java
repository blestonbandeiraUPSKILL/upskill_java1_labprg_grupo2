package t4j.ws.dto;

import t4j.ws.domain.Rolename;
import t4j.ws.domain.Sessao;
import t4j.ws.domain.Utilizador;

import java.util.ArrayList;
import java.util.List;

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

    public static ListaRolenamesDTO listaRolenames2ListaRolenamesDTO(List<Rolename> rolenames) {
        List<RolenameDTO> rolenamesDTO = new ArrayList<RolenameDTO>();

        for(Rolename rolename : rolenames) {
            try {
                RolenameDTO rolenameDTO = rolename2RolenameDTO(rolename);
                rolenamesDTO.add(rolenameDTO);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                exception.getMessage();
            }
        }

        ListaRolenamesDTO listaRolenamesDTO = new ListaRolenamesDTO();
        listaRolenamesDTO.setRolenamesDTO(rolenamesDTO);

        return listaRolenamesDTO;
    }

    public static RolenameDTO rolename2RolenameDTO(Rolename rolename) {
        RolenameDTO rolenameDTO = new RolenameDTO();

        if(rolename.getIdRolename() != 0) {
            rolenameDTO.setIdRolename(rolename.getIdRolename());
        }
        rolenameDTO.setDesignacao(rolename.getDesignacao());
        rolenameDTO.setDescricao(rolename.getDescricao());

        return rolenameDTO;
    }

    public static Rolename rolenameDTO2Rolename(RolenameDTO rolenameDTO) {
        Rolename rolename = new Rolename();

        if(rolenameDTO.getIdRolename() != 0) {
            rolename.setIdRolename(rolenameDTO.getIdRolename());
        }
        rolename.setDesignacao(rolenameDTO.getDesignacao());
        rolename.setDescricao(rolenameDTO.getDescricao());

        return rolename;
    }
}
