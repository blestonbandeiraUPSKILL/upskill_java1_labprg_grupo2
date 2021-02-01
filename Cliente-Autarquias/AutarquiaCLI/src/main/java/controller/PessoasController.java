package controller;

import dto.ErroDTO;
import dto.ListaPessoaDTO;
import dto.Mapper;
import dto.PessoaDTO;
import model.Pessoa;
import network.*;
import utils.Response;
import xml.XmlHandler;

import java.util.ArrayList;

public class PessoasController {

    public static Response getPesssoas(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                ListaPessoaDTO listaPessoaDTO = XmlHandler.deSerializeXML2ListaPessoaDTO(httpResponse.getBody());
                ArrayList<Pessoa> pessoas = Mapper.listaPessoaDTO2ListaPessoa(listaPessoaDTO);
                response = new Response(HttpStatusCode.OK, pessoas);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response getPessoa(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                PessoaDTO pessoaDTO = XmlHandler.deSerializeXML2PessoaDTO(httpResponse.getBody());
                Pessoa pessoa = Mapper.pessoaDTO2Pessoa(pessoaDTO);
                response = new Response(HttpStatusCode.OK, pessoa);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response addPessoa(String uri, PessoaDTO pessoaDTO) {
        Response response = null;
        final String body = XmlHandler.serializePessoaDTO2XML(pessoaDTO);
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, body);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch(httpResponse.getStatus()){
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

    public static Response updatePessoa(String uri, PessoaDTO pessoaDTO) {
        Response response = null;
        final String body = XmlHandler.serializePessoaDTO2XML(pessoaDTO);
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

    public static Response deletePessoa(String uri) {
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
}
