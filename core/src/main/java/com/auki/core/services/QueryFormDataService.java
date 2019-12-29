package com.auki.core.services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public interface QueryFormDataService {
	
	public JSONObject getDbData() throws SQLException;

	
}