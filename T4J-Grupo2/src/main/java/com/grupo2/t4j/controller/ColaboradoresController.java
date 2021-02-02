package com.grupo2.t4j.controller;

import com.grupo2.t4j.dto.ColaboradorDTO;
import com.grupo2.t4j.dto.ErroDTO;
import com.grupo2.t4j.network.*;
import com.grupo2.t4j.utils.Response;
import com.grupo2.t4j.xml.XmlHandler;

public class ColaboradoresController {

    public static Response getColaboradores(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

           /* case HttpStatusCode.OK:
                ListaColaboradorDTO listaColaboradorDTO = XmlHandler.deSerializeXML2ListaColaboradorDTO(httpResponse.getBody());
                ArrayList<Colaborador> colaboradores = Mapper.listaColaboradorDTO2ListaColaborador(listaColaboradorDTO);
                response = new Response(HttpStatusCode.OK, colaboradores);
                break;*/

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response getColaborador(String uri) {
        Response response = null;

        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

           /* case HttpStatusCode.OK:
                ColaboradorDTO colaboradorDTO = XmlHandler.deSerializeXML2ColaboradorDTO(httpResponse.getBody());
                Colaborador colaborador = Mapper.colaboradorDTO2Colaborador(colaboradorDTO);
                response = new Response(HttpStatusCode.OK, colaborador);
                break;*/

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
        }
        return response;
    }

    public static Response addColaborador(String uri, ColaboradorDTO colaboradorDTO) {
        Response response = null;
        final String body = XmlHandler.serializeColaboradorDTO2XML(colaboradorDTO);

        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, uri, body);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.Created:
                response = new Response(HttpStatusCode.Created, null);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response updateColaborador(String uri, ColaboradorDTO colaboradorDTO) {
        Response response = null;
        final String body = XmlHandler.serializeColaboradorDTO2XML(colaboradorDTO);
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
        return response;
    }

    public static Response deleteColaborador(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.DELETE, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch(httpResponse.getStatus()) {

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
}
