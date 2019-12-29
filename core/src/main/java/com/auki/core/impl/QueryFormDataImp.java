package com.auki.core.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.auki.core.services.GetConnectionService;
import com.auki.core.services.QueryFormDataService;

import com.auki.core.models.SimpleFormModel;


@Component(service = QueryFormDataService.class, immediate=true)
public class QueryFormDataImp implements QueryFormDataService  {

	@Reference
	GetConnectionService getConnectionService;
	


	@Override
	public JSONObject getDbData() throws SQLException {
		
		//List<SimpleFormModel> queryResultList = new ArrayList<>();

		Connection c = null;
		c = getConnectionService.getConnection("dataSourceName");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select * FROM simpleform";
		ps = c.prepareStatement(query);
		rs = ps.executeQuery();
		JSONObject objToReturn = new JSONObject();
		int count = 0;
		
	
	        

		while (rs.next()) 
		{
			String firstName = rs.getString(1);
			String lastName = rs.getString(2);
			String birthMonth = rs.getString(3);
			String birthDay = rs.getString(4);
			String birthYear = rs.getString(5);
			String phoneNumber = rs.getString(6);
			String zipCode = rs.getString(7);
			String email = rs.getString(8);
			String radioValue = rs.getString(9);
			String status = rs.getString(10);
			String formId = rs.getString(11);
			String dob = rs.getString(12);
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("formId", formId);
				obj.put("firstName", firstName);
				obj.put("lastName", lastName);
				obj.put("birthMonth", birthMonth);
				obj.put("birthDay", birthDay);
				obj.put("birthYear", birthYear);
				obj.put("phoneNumber", phoneNumber);
				obj.put("zipCode", zipCode);
				obj.put("email", email);
				obj.put("radioValue", radioValue);
				obj.put("status", status);
				obj.put("dob", dob);
				objToReturn.put(Integer.toString(count), obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			count++;
			
			//SimpleFormModel formData = new SimpleFormModel(firstName, lastName, birthMonth, birthDay, birthYear, phoneNumber, zipCode, email, radioValue); 
			
			//queryResultList.add(formData);
			

		}
		try
        {
          c.close();
        }
          
          catch (SQLException e) {
            e.printStackTrace();
          }

		return objToReturn;
	}

}
