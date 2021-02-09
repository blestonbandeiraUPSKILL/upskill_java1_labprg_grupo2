package com.grupo2.t4j.api;

import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.network.*;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Classe responsável pela ligação e pelos pedidos entre a actual aplicação e a API de gestão de utilizadores
 */
public class UsersAPIAdapter implements Serializable {

    private String app_context;
    private final String app_key;

    /**
     *
     * Inicializa uma UsersAPIAdapter
     *
     * @param app_key a app_key
     */
    public UsersAPIAdapter(String app_key) {
        this.app_key = app_key;
    }

    /**
     *
     * Devolve o context
     *
     * @return context
     */
    public String getContext() {
        if (app_context == null || app_context.equals("")) {
            String url = "context?app_key=" + app_key;
            HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
            HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
            switch (httpResponse.getStatus()) {
                case HttpStatusCode.OK:
                    break;
                case HttpStatusCode.Conflict:

                    break;
            }
            JSONObject bodyJSON = new JSONObject(httpResponse.getBody().replaceAll( "\\[|\\]", ""));
            app_context = bodyJSON.getString("app_context");
        }
        return app_context;
    }

    public boolean login(String user_id, String password) {

        String url = "login?app_context=" + getContext() + "&user_id=" + user_id + "&password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public boolean logout() {
        String url = "logout?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;

        case HttpStatusCode.Conflict:
            return false;
        }
        return false;
    }

    public boolean registerUser(String username, Email email, Password password) {
        String url = "registerUser?app_context=" + getContext() + "&username=" + username + "&email=" + email
                + "&password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                return true;

            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public boolean registerUserWithRoles(String username, Email email, Password password, String rolenames) {
        String url = "registerUserWithRoles?app_context=" + getContext() + "&username=" + username + "&email=" + email.getEmailText()
                + "&password=" + password.getPasswordText() + "&rolenames=" + rolenames;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                return true;

            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public String getSession() {
        String url = "session?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {

            case HttpStatusCode.OK:
                break;

            case HttpStatusCode.Conflict:
                break;
        }
        return httpResponse.getBody().replaceAll("\\[|\\]", "");
    }
}
