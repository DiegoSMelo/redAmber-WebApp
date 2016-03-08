package br.com.sistema.redAmber.util;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public abstract class RestUtil {
	
	public static String retornaJson(String url){
		
		Client c = new Client();
	    WebResource wr = c.resource(url);
	    String json = wr.get(String.class);
	    
	    Gson gson = new Gson();
	    
		return gson.toJson(json);
		
	}
}
