package main;

import java.sql.*;
import java.util.Properties;

public class DBConn {
	
	protected Connection conn;
    
	public DBConn () {
    
    }
    
    public void connect() {
    	try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.mysql.cj.jdbc.Driver"); // when you are using MySQL 8.0.	    
            // Properties for user and password.
            Properties p = new Properties(); // 123
            p.put("user", "root");
            p.put("password", "tomat123");           
            // conn = DriverManager.getConnection("jdbc:mysql://mysql.ansatt.ntnu.no/sveinbra_ektdb?autoReconnect=true&useSSL=false",p);
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/filmprosjekt?autoReconnect=true&useSSL=false&serverTimezone=UTC", p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}
