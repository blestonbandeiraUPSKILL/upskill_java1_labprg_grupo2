package com.grupo2.t4j.model;

import com.grupo2.t4j.network.*;

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
                    brea
            }
        }
    }
}
