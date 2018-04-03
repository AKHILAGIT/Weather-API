package com.RestAPInew;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.JSONP;

import javax.ws.rs.core.Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.List;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

@Path("/historical")
public class Hello {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)		
	public List<PointBean> hello() throws ClassNotFoundException, SQLException
	{
		//System.out.println(Dao.fetch());
		return  Dao.fetch();
		
		
	}
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{date}")
	
	public Response historyTemp(@PathParam("date") String dt) throws ClassNotFoundException, SQLException{
		/*List l=;*/
		TempBean hstmp=Dao.fetchTemp(dt);
		
		
		if(hstmp == null){
			return Response.status(Status.NOT_FOUND).entity("").build();
		}
		else{
			String temp = "{\"DATE\": \""+hstmp.getDt()+"\",\"TMAX\": "+hstmp.getTmax()+",\"TMIN\": "+hstmp.getTmin()+"}";
		return Response.status(Status.OK).entity(temp).build();
		}
	}	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public Response tempADD(TempBean tmp) throws ClassNotFoundException, SQLException{
		PointBean pbn=Dao.insertTemp(tmp);
		String temp = "{\"DATE\": \""+pbn.getPo()+"\"}";
		return Response.status(Status.CREATED).entity(temp).build();		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{date}")
	public String tempDel(@PathParam("date") String dt) throws ClassNotFoundException, SQLException{
		return Dao.deleteTemp(dt);	
		
	}
}
