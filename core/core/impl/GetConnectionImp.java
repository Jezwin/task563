package com.auki.core.impl;

import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.auki.core.services.GetConnectionService;
import com.day.commons.datasource.poolservice.DataSourcePool;

@Component(service = GetConnectionService.class, immediate=true)
public class GetConnectionImp implements GetConnectionService  {
	
	 @Reference
	 private DataSourcePool source;

	@Override
	public Connection getConnection(String dataSourceName) {
		
		System.out.println("Hello from getConnection");
        DataSource dataSource = null;
        Connection con = null;
        try
        {
            //Inject the DataSourcePool right here! 
            dataSource = (DataSource) source.getDataSource("Task569SQL");
            con = dataSource.getConnection();
            return con;
              
          }
        catch (Exception e)
        {
            e.printStackTrace(); 
        }
            return null; 
		
	}
	
	
}
