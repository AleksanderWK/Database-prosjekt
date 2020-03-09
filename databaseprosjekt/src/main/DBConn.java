package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;

public class DBConn {
	
	protected Connection conn;
    
	public DBConn () {
    
    }
    
    public void connect() {
    	try {
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("user: ");
    		String user = scanner.nextLine();
    		System.out.println("password: ");
    		String password = scanner.nextLine();
    		System.out.println("Schema Name: ");
    		String schemaName = scanner.nextLine();
    		
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.mysql.cj.jdbc.Driver"); // when you are using MySQL 8.0.    
            // Properties for user and password. 
            Properties p = new Properties(); // 123
            p.put("user", user);
            p.put("password", password);           
            // conn = DriverManager.getConnection("jdbc:mysql://mysql.ansatt.ntnu.no/sveinbra_ektdb?autoReconnect=true&useSSL=false",p);
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + schemaName + "?autoReconnect=true&useSSL=false&serverTimezone=UTC", p);
    	} catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}
