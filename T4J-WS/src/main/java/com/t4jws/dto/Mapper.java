package com.t4jws.dto;

import com.t4jws.model.Email;
import com.t4jws.model.Password;
import com.t4jws.model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static EmailDTO email2EmailDTO(Email email) throws NullPointerException{
        return new EmailDTO(email.getEmailText());
    }

    public static Email emailDTO2Email(EmailDTO emailDTO) throws NullPointerException{
        return new Email(emailDTO.getEmailText());
    }

    public static PasswordDTO password2PasswordDTO(Password password) throws NullPointerException {
        return new PasswordDTO(password.getPasswordText());
    }

    public static Password passwordDTO2Password(PasswordDTO passwordDTO) throws NullPointerException {
        return new Password(passwordDTO.getPasswordText());
    }

    public static UtilizadorDTO utilizador2utilizadorDTO(Utilizador utilizador) throws NullPointerException {
        return new UtilizadorDTO((email2EmailDTO(utilizador.getEmail())), utilizador.getNome(), password2PasswordDTO(utilizador.getPassword()));
    }

    public static Utilizador utilizadorDTO2Utilizador(UtilizadorDTO utilizadorDTO) throws NullPointerException {
        return new Utilizador(emailDTO2Email(utilizadorDTO.getEmailDTO()), utilizadorDTO.getNome(), passwordDTO2Password(utilizadorDTO.getPasswordDTO()));
    }

    public static ListaUtilizadoresDTO listaUtilizadores2ListaUtilizadoresDTO(List<Utilizador> utilizadores) throws NullPointerException {
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

        ListaUtilizadoresDTO listaUtilizadoresDTO = new ListaUtilizadoresDTO();

        listaUtilizadoresDTO.setUtilizadores(utilizadoresDTO);

        return listaUtilizadoresDTO;
    }

    public static ArrayList<Utilizador> listaUtilizadoresDTO2ListaUtilizadores(ListaUtilizadoresDTO listaUtilizadoresDTO) throws NullPointerException{
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        ArrayList<UtilizadorDTO> utilizadoresDTO = listaUtilizadoresDTO.getUtilizadores();

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
 /*
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
