package com.grupo2.t4j.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.grupo2.t4j.dto.*;

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

    public static String serializeUtilizadorDTO2XML(UtilizadorDTO utilizadorDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(utilizadorDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static UtilizadorDTO deSerializeXML2UtilizadorDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            UtilizadorDTO data = xmlMapper.readValue(xmlData, UtilizadorDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeListaUtilizadorDTO2XML(ListaUtilizadorDTO listaUtilizadorDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(listaUtilizadorDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ListaUtilizadorDTO deSerializeXML2ListaUtilizadorDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ListaUtilizadorDTO data = xmlMapper.readValue(xmlData, ListaUtilizadorDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeColaboradorDTO2XML(ColaboradorDTO colaboradorDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(colaboradorDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ColaboradorDTO deSerializeXML2ColaboradorDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ColaboradorDTO data = xmlMapper.readValue(xmlData, ColaboradorDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serializeListaColaboradorDTO2XML(ListaColaboradorDTO listaColaboradorDTO) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(listaColaboradorDTO);
            return xml;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ListaColaboradorDTO deSerializeXML2ListaColaboradorDTO(String xmlData) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ListaColaboradorDTO data = xmlMapper.readValue(xmlData, ListaColaboradorDTO.class);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XmlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
