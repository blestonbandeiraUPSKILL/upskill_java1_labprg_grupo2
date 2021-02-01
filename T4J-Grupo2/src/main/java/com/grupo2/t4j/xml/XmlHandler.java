package com.grupo2.t4j.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.grupo2.t4j.dto.ErroDTO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlHandler {

    public static String serializeErroDTO2XML(ErroDTO erroDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(erroDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ErroDTO deSerializeXML2ErroDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ErroDTO data = xmlMapper.readValue(xmlData, ErroDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*public static String serializePessoaDTO2XML(PessoaDTO pessoaDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(pessoaDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static PessoaDTO deSerializeXML2PessoaDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            PessoaDTO data = xmlMapper.readValue(xmlData, PessoaDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeListaPessoaDTO2XML(ListaPessoaDTO listaPessoaDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(listaPessoaDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ListaPessoaDTO deSerializeXML2ListaPessoaDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ListaPessoaDTO data = xmlMapper.readValue(xmlData, ListaPessoaDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeFuncionarioDTO2XML(FuncionarioDTO funcionarioDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(funcionarioDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static FuncionarioDTO deSerializeXML2FuncionarioDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            FuncionarioDTO data = xmlMapper.readValue(xmlData, FuncionarioDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeListaFuncionarioDTO2XML(ListaFuncionarioDTO listaFuncionarioDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(listaFuncionarioDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ListaFuncionarioDTO deSerializeXML2ListaFuncionarioDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ListaFuncionarioDTO data = xmlMapper.readValue(xmlData, ListaFuncionarioDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
}
