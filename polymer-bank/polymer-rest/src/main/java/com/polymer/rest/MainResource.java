package com.polymer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class MainResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String main(){
		return "logged";
	}
	
}
