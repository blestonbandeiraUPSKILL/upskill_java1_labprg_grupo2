package com.grupo2.t4j.network;

public class HttpRequest {
    private HttpRequestType type;
    private String url;

    public HttpRequest(HttpRequestType type, String url) {
        this.type = type;
        this.url = url;

    }

    public HttpRequestType getType() {
        return type;
    }

    public void setType(HttpRequestType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
