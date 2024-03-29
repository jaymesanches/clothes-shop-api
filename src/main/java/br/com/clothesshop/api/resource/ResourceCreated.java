package br.com.clothesshop.api.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceCreated extends ApplicationEvent {
	private static final long serialVersionUID = 7508708996107087347L;
	
	private HttpServletResponse response;
    private long idOfNewResource;
 
    public ResourceCreated(Object source, 
      HttpServletResponse response, long idOfNewResource) {
        super(source);
 
        this.response = response;
        this.idOfNewResource = idOfNewResource;
    }
 
    public HttpServletResponse getResponse() {
        return response;
    }
    public long getIdOfNewResource() {
        return idOfNewResource;
    }
}
