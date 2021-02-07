package controller;

import dto.ErroDTO;
import dto.FuncionarioDTO;
import dto.ListaFuncionarioDTO;
import dto.Mapper;
import model.Funcionario;
import network.*;
import utils.Response;
import xml.XmlHandler;

import java.util.ArrayList;

public class FuncionariosController {

    public static Response getFuncionarios(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                ListaFuncionarioDTO listaFuncionarioDTO = XmlHandler.deSerializeXML2ListaFuncionarioDTO(httpResponse.
                        getBody());
                ArrayList<Funcionario> funcionarios = Mapper.listaFuncionarioDTO2ListaFuncionario(listaFuncionarioDTO
                );
                response = new Response(HttpStatusCode.OK, funcionarios);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response getFuncionario(String uri) {
        Response response = null;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, uri, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                FuncionarioDTO funcionarioDTO = XmlHandler.deSerializeXML2FuncionarioDTO(httpResponse.getBody());
                Funcionario funcionario = Mapper.funcionarioDTO2Funcionario(funcionarioDTO);
                response = new Response(HttpStatusCode.OK, funcionario);
                break;

            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }

    public static Response addFuncionario(String uri, FuncionarioDTO funcionarioDTO) {
        Response response = null;
        final String body = XmlHandler.serializeFuncionarioDTO2XML(funcionarioDTO);
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

    public static Response updateFuncionario(String uri, FuncionarioDTO funcionarioDTO) {
        Response response = null;
        final String body = XmlHandler.serializeFuncionarioDTO2XML(funcionarioDTO);
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

    public static Response deleteFuncionario(String uri) {
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
