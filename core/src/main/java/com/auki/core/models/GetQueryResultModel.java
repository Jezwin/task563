package com.auki.core.models;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.json.JSONObject;

import com.auki.core.services.QueryFormDataService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GetQueryResultModel {
	
	@Inject
	private QueryFormDataService queryFormDataService;
	
	//private ArrayList<SimpleFormModel> returnList = new ArrayList<>();
	
	private JSONObject returnList;
	
	public JSONObject getReturnList() {
		return returnList;
	}



	private String text;
	

	public String getText() {
		return text;
	}



//	public ArrayList<SimpleFormModel> getReturnList() {
//		return returnList;
//	}



	@PostConstruct
	 protected void init() {
		
		text = "abc";
		
		try {
			JSONObject returnList = queryFormDataService.getDbData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
