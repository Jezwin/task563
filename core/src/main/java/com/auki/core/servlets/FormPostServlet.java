package com.auki.core.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.auki.core.services.QueryFormDataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Form Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/task",
        "sling.servlet.extensions=" + "json"
})

public class FormPostServlet extends SlingSafeMethodsServlet  {

	private static final long serialVersionUID = 1L;
	
	@Reference
	private QueryFormDataService queryFormDataService;
 
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
	    	

			
			JSONObject returnList = null;
			try {
				returnList = queryFormDataService.getDbData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    
    		resp.setContentType("application/json");
 	        resp.setCharacterEncoding("utf-8");
    		resp.getWriter().write(returnList.toString());
    		
    		
    		
        
    }
}
