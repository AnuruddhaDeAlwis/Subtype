package com.data.queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HandlingQueries {

	
	public static ArrayList getAllAttributeNamesOfATable(String table){
		ArrayList attributeNames = new ArrayList();
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/sugarcrm","root","");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SHOW COLUMNS FROM "+table);  
			while(rs.next()) 
				//System.out.println(rs.getObject(1));
				attributeNames.add(rs.getObject(1)); 
				con.close();  
		}catch(Exception e){ 
			System.out.println(e);
			}  
		
		return attributeNames;
	}
	
	
	
	public static ArrayList getValuesForGivenAttribute(String table, String attribute, int count){
		
		ArrayList values = new ArrayList();
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/sugarcrm","root","");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT DISTINCT "+attribute+" FROM "+table+" LIMIT 0,"+count);  
			while(rs.next()) 
				//System.out.println(rs.getObject(1));
				values.add(rs.getObject(1)); 
				con.close();  
		}catch(Exception e){ 
			System.out.println(e);
			}  
		
		return values;
	}
	
	
	
public static String getAllValuesOfTabel(String table){
		
		String data = "";
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/sugarcrm","root","");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT *  FROM "+table);  
			while(rs.next()) 
				//System.out.println(rs.getObject(1));
				data = data + rs.getObject(1).toString();
				con.close();  
		}catch(Exception e){ 
			System.out.println(e);
			}
				
		return data;
			 
		
		
	}
	
	
}
