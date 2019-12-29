package com.auki.core.impl;
 
import org.apache.commons.mail.EmailException;
import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.engine.EngineConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import com.auki.core.services.GetConnectionService;
import com.auki.core.services.HandleForm;
import com.auki.core.services.MailService;
//Add the DataSourcePool package
import com.day.commons.datasource.poolservice.DataSourcePool; 
  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
  
  
import java.sql.SQLException;
import javax.sql.DataSource;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
 
@Component
@Designate(ocd = HandleFormImp.Config.class)
public class HandleFormImp implements HandleForm{
	
	@ObjectClassDefinition(name = "Mail Notification Config", description = "Test" )
	public static @interface Config {
		@AttributeDefinition(name = "Admin Email")
		String admin_email() default "jezwinvarghese@gmail.com";	
		
		@AttributeDefinition(name = "Subject")
		String subject() default "Form Data Entry";
		
	}
     
    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
    @Reference
    private DataSourcePool source;
    
    @Reference
    private MailService mailService;
    
    @Reference
    GetConnectionService getConnectionService;
    
    private String admin_email;
    
    private String subject;
    
    @Activate
	protected void activate(final Config config) {
		this.admin_email = config.admin_email();
		this.subject = config.subject();
	}
  
    //Inject the Form Data into a database! 
    @Override
    public void injestFormDataDB(String firstName, String lastName, String birthMonth, String birthDay, String birthYear, String phoneNumber, String zipCode, String email, String radioButtonValue)
{
         
        //Simply write out the values that are posted from the AEM form to the AEM log file
        log.info(firstName+lastName+email) ;
        System.out.println("Hello from Service");
        
        Random rand = new Random();
        //int mm = java.time.Month.valueOf(birthMonth.toUpperCase()).getValue();
        String dateOfBirth = birthMonth+" "+birthDay+", "+birthYear;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateOfBirth, formatter);
        System.out.println(date); 
         
        Connection c = null;
         
        int rowCount= 0; 
        try {
                          
              // Create a Connection object
              c =  getConnectionService.getConnection("dataSourceName");
             
               ResultSet rs = null;
               Statement s = c.createStatement();
               Statement scount = c.createStatement();
                   
               //Use prepared statements to protected against SQL injection attacks
               PreparedStatement pstmt = null;
               PreparedStatement ps = null; 
                             
              
                 
            
                 
               String insert = "INSERT INTO simpleform(firstname,lastname, birthmonth, birthday, birthyear, phonenumber, zipcode, email, radiovalue, status, formid, dob) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
               ps = c.prepareStatement(insert);
                 
               ps.setString(1, firstName); 
               ps.setString(2, lastName);
               ps.setString(3, birthMonth);
               ps.setString(4, birthDay);
               ps.setString(5, birthYear);
               ps.setString(6, phoneNumber);
               ps.setString(7, zipCode);
               ps.setString(8, email);
               ps.setString(9, radioButtonValue);
               ps.setString(10, "New");
               ps.setInt(11, rand.nextInt(10000));
               ps.setDate(12, java.sql.Date.valueOf(date));
               
              
               ps.execute();
                
        }
        catch (Exception e) {
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
        String adminMessage = "First Name: "+ firstName + "\n Last Name: "+lastName+ "\n Date of Birth: "+ date + "\n Phone Number: "+phoneNumber+"\n ZIP Code: "+zipCode+"\n Email: "+email+"\n Job Type: "+radioButtonValue;
        try {
			mailService.sendMail(email, subject, "Thank You");
			mailService.sendMail(admin_email, subject, adminMessage);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
  }

  
}
