package com.polymer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;

@Api(value="Main resource")
@Path("/main")
public class MainResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String main(){
		return "test";
	}
	
}
