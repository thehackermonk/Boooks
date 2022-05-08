package com.hacker.boooks.bean;

public class ResponseEntity<T> {

    private T response;
    private int httpStatusCode;

    public ResponseEntity() {
    }

    public ResponseEntity(T response, int httpStatusCode) {
        this.response = response;
        this.httpStatusCode = httpStatusCode;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
