package com.grupo2.t4j.model;

import com.grupo2.t4j.network.*;
import org.json.JSONObject;

public class UsersAPIAdapter {

    private String app_context;
    private String app_key;

    public UsersAPIAdapter(String app_key) {
        this.app_key = app_key;
    }

    private String getContext() {
        if(app_context == "" || app_context.equals("")) {
            String url = "/context?app_key=" + app_key;
            HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url, "");
            HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

            switch (httpResponse.getStatus()) {

                case HttpStatusCode.OK:
                    break;

                case HttpStatusCode.Conflict:
                    break;
            }
            JSONObject bodyJSON = new JSONObject(httpResponse.getBody().replaceAll("\\[|\\]", ""));
            app_context = bodyJSON.getString("app_context");
        }
        return app_context;
    }

    public boolean login(String user_id, Password password) {
        String url = "/login?app_contect=" + getContext() + "?username=" + user_id + "?password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url, "");
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);

        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false
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


        }
    }
}
