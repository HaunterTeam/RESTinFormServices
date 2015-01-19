package project.resources;

import project.getfacebookinfo.FacebookService;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/facebook")
public class FacebookRes {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
	public JSONObject getFacebookProfile(@QueryParam("token") String token) 
			throws MalformedURLException, JSONException, IOException {
		
		System.out.println("here");
            
            FacebookService fs = new FacebookService();
            System.err.println(fs.getInfo().toString());
            return fs.getInfo();
	}
}