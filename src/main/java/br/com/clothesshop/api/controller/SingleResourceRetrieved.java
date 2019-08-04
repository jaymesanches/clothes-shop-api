package br.com.clothesshop.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class SingleResourceRetrieved extends ApplicationEvent {
    private HttpServletResponse response;
 
    public SingleResourceRetrieved(Object source, HttpServletResponse response) {
        super(source);
 
        this.response = response;
    }
 
    public HttpServletResponse getResponse() {
        return response;
    }
}
