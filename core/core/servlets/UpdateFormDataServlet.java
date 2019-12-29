package com.auki.core.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.auki.core.services.GetConnectionService;



@Component(service=Servlet.class,
property={
		Constants.SERVICE_DESCRIPTION + "=Form Update Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths="+ "/bin/taskpost",
		"sling.servlet.extensions=" + "json"
})

public class UpdateFormDataServlet extends SlingAllMethodsServlet  {

	private static final long serialVersionUID = 1L;

	@Reference
	GetConnectionService getConnectionService;

	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException, IOException {

		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String birthMonth = req.getParameter("birthmonth");
		String birthDay = req.getParameter("birthday");
		String birthYear = req.getParameter("birthyear");
		String phoneNumber = req.getParameter("phonenumber");
		String zipCode = req.getParameter("zipcode");
		String email = req.getParameter("email");
		String radioButtonValue = req.getParameter("radiovalue");
		String status = req.getParameter("status");
		String formid = req.getParameter("formid");
		String dob = req.getParameter("dob");

		Connection c = null;
		c = getConnectionService.getConnection("dataSourceName");
		PreparedStatement ps = null; 





		String update = "UPDATE simpleform SET firstname=?,lastname=?, birthmonth=?, birthday=?, birthyear=?, phonenumber=?, zipcode=?, email=?, radiovalue=?, status=?, dob=? WHERE formid=?";
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, firstName); 
			ps.setString(2, lastName);
			ps.setString(3, birthMonth);
			ps.setString(4, birthDay);
			ps.setString(5, birthYear);
			ps.setString(6, phoneNumber);
			ps.setString(7, zipCode);
			ps.setString(8, email);
			ps.setString(9, radioButtonValue);
			ps.setString(10, status);
			ps.setString(12, formid);
			ps.setDate(11, Date.valueOf(dob));


			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try
			{
				c.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}


		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("Success");




	}
}
