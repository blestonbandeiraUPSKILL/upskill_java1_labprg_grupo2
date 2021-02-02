package com.grupo2.t4j.controller;

import com.grupo2.t4j.dto.ErroDTO;
import com.grupo2.t4j.dto.ListaUtilizadorDTO;
import com.grupo2.t4j.dto.Mapper;
import com.grupo2.t4j.dto.UtilizadorDTO;
import com.grupo2.t4j.model.Utilizador;
import com.grupo2.t4j.network.*;
import com.grupo2.t4j.utils.Response;
import com.grupo2.t4j.xml.XmlHandler;

import java.util.ArrayList;

public class UtilizadoresController {

   /* public static Response getUtilizadores(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                ListaUtilizadorDTO listaUtilizadorDTO = XmlHandler.deSerializeXML2ListaUtilizadorDTO(httpResponse.getBody());
                ArrayList<Utilizador> utilizadores = Mapper.listaUtilizadorDTO2ListaUtilizador(listaUtilizadorDTO);
                response = new Response(HttpStatusCode.OK, utilizadores);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
        }
        return  response;
    }

    public static Response getUtilizador(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                UtilizadorDTO utilizadorDTO = XmlHandler.deSerializeXML2UtilizadorDTO(httpResponse.getBody());
                Utilizador utilizador = Mapper.utilizadorDTO2Utilizador(utilizadorDTO);
                response = new Response(HttpStatusCode.OK, utilizador);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response addUtilizador(String uri, UtilizadorDTO utilizadorDTO) {
        Response response = null;
        final String body = XmlHandler.serializeUtilizadorDTO2XML(utilizadorDTO);
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, body);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch(httpResponse.getStatus()) {

            case HttpStatusCode.Created:
                response = new Response(HttpStatusCode.Created, null);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
        }
        return response;
    }

    public static Response updateUtilizador(String uri, UtilizadorDTO utilizadorDTO) {
        Response response = null;
        final String body = XmlHandler.serializeUtilizadorDTO2XML(utilizadorDTO);
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.PUT, uri, body);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                response = new Response(HttpStatusCode.OK, null);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return  response;
    }

    public static Response deleteUtilizador(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.DELETE, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                response = new Response(HttpStatusCode.OK, null);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }
*/

}
