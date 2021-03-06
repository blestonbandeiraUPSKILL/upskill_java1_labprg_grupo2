package com.grupo2.t4j.utils;

public class Response {

    private int status;
    private Object body;

    public Response(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public Object getBody() {
        return body;
    }
}
