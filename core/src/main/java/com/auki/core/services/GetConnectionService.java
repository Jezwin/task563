package com.auki.core.services;

import java.sql.Connection;

public interface GetConnectionService {
	 public Connection getConnection(String dataSourceName);
}
