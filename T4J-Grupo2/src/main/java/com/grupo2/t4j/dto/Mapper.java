package com.grupo2.t4j.dto;

import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Utilizador;

import java.util.ArrayList;

public class Mapper {

    /*public static DataDTO data2DataDTO(Data data) throws NullPointerException {
        return new DataDTO(data.getDia(), data.getMes(), data.getAno());
    }

    public static Data dataDTO2Data(DataDTO dataDTO) throws NullPointerException {
        return new Data(dataDTO.getDia(), dataDTO.getMes(), dataDTO.getAno());
    }

    public static UtilizadorDTO utilizador2utilizadorDTO(Utilizador utilizador) throws NullPointerException {
        return new UtilizadorDTO(utilizador.getNome(), utilizador.getEmail(), utilizador.getPassword());
    }

    public static Utilizador utilizadorDTO2Utilizador(UtilizadorDTO utilizadorDTO) throws NullPointerException {
        return new Utilizador(utilizadorDTO.getNome(), utilizadorDTO.getEmail(), utilizadorDTO.getPassword());
    }

    public static ListaUtilizadorDTO listaUtilizador2ListaUtilizadorDTO(ArrayList<Utilizador> utilizadores) throws NullPointerException {
        ArrayList<UtilizadorDTO> utilizadoresDTO = new ArrayList<>();

        for (Utilizador utilizador : utilizadores) {
            try {
                UtilizadorDTO utilizadorDTO = utilizador2utilizadorDTO(utilizador);
                utilizadoresDTO.add(utilizadorDTO);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }

        ListaUtilizadorDTO listaUtilizadorDTO = new ListaUtilizadorDTO();
        listaUtilizadorDTO.setUtilizadores(utilizadoresDTO);
        return listaUtilizadorDTO;
    }

    public static ArrayList<Utilizador> listaUtilizadorDTO2ListaUtilizador(ListaUtilizadorDTO listaUtilizadorDTO) throws NullPointerException{
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        ArrayList<UtilizadorDTO> utilizadoresDTO = listaUtilizadorDTO.getUtilizadores();

        for (UtilizadorDTO utilizadorDTO : utilizadoresDTO) {
            try {
                Utilizador utilizador = utilizadorDTO2Utilizador(utilizadorDTO);
                utilizadores.add(utilizador);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }
        return utilizadores;
    }

    public static ColaboradorDTO colaborador2ColaboradorDTO (Colaborador colaborador) throws NullPointerException {
        return new ColaboradorDTO(colaborador.getNome(),
                colaborador.getEmail(),
                colaborador.getPassword(),
                colaborador.getFuncao(),
                colaborador.getTelefone());
    }

    public static Colaborador colaboradorDTO2Colaborador (ColaboradorDTO colaboradorDTO) throws NullPointerException {
        return new Colaborador(colaboradorDTO.getNome(),
                colaboradorDTO.getEmail(),
                colaboradorDTO.getPassword(),
                colaboradorDTO.getFuncao(),
                colaboradorDTO.getTelefone());
    }
    public static ListaColaboradorDTO listacolaborador2ListaColaboradorDTO (ArrayList<Colaborador> colaboradores) throws NullPointerException {
        ArrayList<ColaboradorDTO> colaboradoresDTO = new ArrayList<>();
        for (Colaborador colaborador : colaboradores) {
            try {
                ColaboradorDTO colaboradorDTO = colaborador2ColaboradorDTO(colaborador);
                colaboradoresDTO.add(colaboradorDTO);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }

        ListaColaboradorDTO listaColaboradorDTO = new ListaColaboradorDTO();
        listaColaboradorDTO.setColaboradores(colaboradoresDTO);
        return listaColaboradorDTO;
    }

    public static ArrayList<Colaborador> listaColaboradorDTO2ListaColaborador (ListaColaboradorDTO listaColaboradorDTO) throws  NullPointerException {
        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        ArrayList<ColaboradorDTO> colaboradoresDTO = new ArrayList<>();

        for(ColaboradorDTO colaboradorDTO : colaboradoresDTO) {
            try {
                Colaborador colaborador = colaboradorDTO2Colaborador(colaboradorDTO);
                colaboradores.add(colaborador);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }
        return colaboradores;
    }

     */
}
