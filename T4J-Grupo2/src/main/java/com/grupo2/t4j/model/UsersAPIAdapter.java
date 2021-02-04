package com.grupo2.t4j.model;

import com.grupo2.t4j.dto.ErroDTO;
import com.grupo2.t4j.network.*;
import com.grupo2.t4j.utils.Response;
import com.grupo2.t4j.xml.XmlHandler;
import org.json.JSONObject;


public class UsersAPIAdapter {

    private String app_context;
    private final String app_key;

    public UsersAPIAdapter(String app_key) {
        this.app_key = app_key;
    }

    public String getContext() {
        Response response = null;
        if(app_context.equals("")) {
            String url = "/context?app_key=" + app_key;
            HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url, "");
            HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

            switch (httpResponse.getStatus()) {

                case HttpStatusCode.OK:
                    JSONObject bodyJSON = new JSONObject(httpResponse.getBody().replaceAll("\\[|\\]", ""));
                    app_context = bodyJSON.getString("app_context");
                    break;

                case HttpStatusCode.Conflict:
                    JSONObject errorJSON = new JSONObject(httpResponse.getBody().replaceAll("\\[|\\]", ""));
                    app_context = errorJSON.getString("error");
                    break;
            }
        }
        return app_context;
    }

    public boolean login(String user_id, Password password) {
        String url = "/login?app_context=" + getContext() + "&user_id=" + user_id + "&password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url, "");
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
        String url = "/logout?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url, "");
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
        String url = "/registerUser?app_context=" + getContext() + "&username=" + username + "&email=" + email +
                "&password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url, "");
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
        String url = "/registerUserWithRoles?app_context=" + getContext() +
                "&username=" + username + "&password=" + password + "&rolenames=" + rolenames;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url, "");
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
        String url = "/session?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url, "");
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
