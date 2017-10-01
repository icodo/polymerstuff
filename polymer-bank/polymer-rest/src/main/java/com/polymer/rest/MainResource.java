package com.polymer.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class MainResource {
	
	
	private Map<String, String> users = new HashMap<String, String>();
	
	@POST
	@Consumes("text/plain")
	@Produces(MediaType.APPLICATION_JSON)
	public Response main(String message){
		System.out.println(message);
		return Response.ok().entity("token: blablabla").build();
	}
	
	private boolean checkUser(String user, String password){
		boolean result = false;
		String pwdSaved = users.get(user);
		if(pwdSaved != null && pwdSaved.equals(password)){
			result = true;
		}
		return result;
	}
	
	@PostConstruct
	private void fillUsers(){
		users.put("ivan", "123");
	}
	
}
