package com.Impl;
import com.model.*;
import com.DAO.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

	@Path("/issue")
	public class IssueImpl {	
		
		@Context HttpServletRequest request;
		
		@POST
		@Path("create")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public void createIssue(JSONObject inputJsonObj) throws Exception
		{	
			String description = inputJsonObj.getString("desc");
			String status = inputJsonObj.getString("status");
			String severity = inputJsonObj.getString("severity");
		    Issue p = new Issue();
	        p.setStatus(status);
	        p.setDescription(description);
	        p.setSeverity(severity);
	           
	     //   MongoClient mongo = (MongoClient) request.getServletContext()
	     //             .getAttribute("MONGO_CLIENT");
	        
	        MongoClientURI uri  = new MongoClientURI("mongodb://arun:arun@ds027155.mongolab.com:27155/issuetracker"); 
            MongoClient mongo = new MongoClient(uri);
	        
	        MongoDBIssueDAO issueDAO = new MongoDBIssueDAO(mongo);
	        issueDAO.createIssue(p);
	        System.out.println("Issue Added Successfully with id="+p.getId());
		}	       
		
		
		@GET
		@Path("readAll")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response readAll() throws Exception
		{
			List<Issue> Issues = new ArrayList<Issue>();
		//	MongoClient mongo = (MongoClient) request.getServletContext()
	     //              .getAttribute("MONGO_CLIENT");
			 MongoClientURI uri  = new MongoClientURI("mongodb://arun:arun@ds027155.mongolab.com:27155/issuetracker"); 
	         MongoClient mongo = new MongoClient(uri);
	        MongoDBIssueDAO issueDAO = new MongoDBIssueDAO(mongo);
		
	        Issues = issueDAO.readAllIssue();
	           
	        GenericEntity<List<Issue>> entity = new GenericEntity<List<Issue>>(Issues) {};
	        Response response = Response.ok(entity).build();
	        return response;
			}
		
		@POST
		@Path("edit")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		
		public Response editIssue(JSONObject inputJsonObj) throws Exception
		{
			String id = inputJsonObj.getString("id");
			String description = inputJsonObj.getString("desc");
			String status = inputJsonObj.getString("status");
			String severity = inputJsonObj.getString("severity");
		  //  MongoClient mongo = (MongoClient) request.getServletContext()
	       //             .getAttribute("MONGO_CLIENT");
			
			 MongoClientURI uri  = new MongoClientURI("mongodb://arun:arun@ds027155.mongolab.com:27155/issuetracker"); 
	         MongoClient mongo = new MongoClient(uri);
			
	        MongoDBIssueDAO issueDAO = new MongoDBIssueDAO(mongo);
	        Issue p = new Issue();
	        p.setId(id);
	        p.setDescription(description);
	        p.setStatus(status);
	        p.setSeverity(severity);
	            
	        issueDAO.updateIssue(p);
	        System.out.println("Issue edited successfully with id=" + id);
	        List<Issue> Issues = new ArrayList<Issue>();
		    Issues = issueDAO.readAllIssue();
		           
		    GenericEntity<List<Issue>> entity = new GenericEntity<List<Issue>>(Issues) {};
		    Response response = Response.ok(entity).build();
		    return response;
		}

		
		@POST
		@Path("delete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteIssue(JSONObject inputJsonObj) throws Exception
		{
			String id = inputJsonObj.getString("issueId");
		//	MongoClient mongo = (MongoClient) request.getServletContext()
	    //               .getAttribute("MONGO_CLIENT");
			
			 MongoClientURI uri  = new MongoClientURI("mongodb://arun:arun@ds027155.mongolab.com:27155/issuetracker"); 
	         MongoClient mongo = new MongoClient(uri);
	        MongoDBIssueDAO issueDAO = new MongoDBIssueDAO(mongo);
	        Issue p = new Issue();
		    p.setId(id);
		    issueDAO.deleteIssue(p);
		    System.out.println("Issue deleted successfully with id=" + id);
		    List<Issue> Issues = new ArrayList<Issue>();
		    Issues = issueDAO.readAllIssue();
	           
	        GenericEntity<List<Issue>> entity = new GenericEntity<List<Issue>>(Issues) {};
	        Response response = Response.ok(entity).build();
	        return response;
			
		}
		
		
}
