package br.com.clothesshop.api.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class SingleResourceRetrieved extends ApplicationEvent {
	private static final long serialVersionUID = -6567201807590162904L;
	
	private HttpServletResponse response;
 
    public SingleResourceRetrieved(Object source, HttpServletResponse response) {
        super(source);
 
        this.response = response;
    }
 
    public HttpServletResponse getResponse() {
        return response;
    }
}
