package com.RestAPInew;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("forecast")
public class Weather {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{date}")
	public List<TempBean> historyTemp(@PathParam("date") String dt) throws ClassNotFoundException, SQLException{
		/*List l=;*/
		return Dao.fetchForcast(dt);
		
		
		
	}
}