package com.data.staticanalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysingExtractedXMLValues {
	static String[] allTablesFrimSQL;
	static List<String> wordList;
	
		public static void identifyTablesFromQueries(String dataReadLocation, String DataWriteLocation){
			String allInfromation = "";
			String allTables = "";
                        String otherTables = "";
			
			try {
			    BufferedReader in = new BufferedReader(new FileReader(dataReadLocation));
			    String str;
			    while ((str = in.readLine()) != null)
			    	allInfromation = allInfromation + str;
			    in.close();
			} catch (IOException e) {
			}
			
			
			//So here we get all the databse table names. If tha particular name is not there then there is no use of adding that 
			//particular value to the allTables.
			wordList = getDatabseTabelNames();
			
			Pattern p = Pattern.compile("((\\w)+\\.+[a-zA-Z_ ]+\\=+[a-zA-Z_ ]+\\.+(\\w+))",Pattern.DOTALL); //Best with all
		
			
			//System.out.println("Log :"+fileEntry);
				
		    	Matcher m = p.matcher(allInfromation);
		    	
		    	while (m.find()) {
		    		String xx = "";
		    	for(int a=0;a<m.groupCount()-1;a++){
		    	//System.out.println("Value: "+m.group(a));
		    	xx = m.group(a);
		    	}
		    	
		    	String[] betweenEqual = xx.split("=");
		    	String[] one = betweenEqual[0].split("\\.");
		    	String[] two = betweenEqual[1].split("\\.");
		    	
		    	String onezero = one[0].replaceAll("\\s+","");
		    	String twozero = two[0].replaceAll("\\s+","");
		    	
		    	if(wordList.contains(onezero) && wordList.contains(twozero)){
		    		allTables = allTables +xx+"@@@";
		    	}else{
                                otherTables = otherTables +xx+"@@@";
                        }
		    	
		    	
		    	//System.out.println(xx);
		    	
		    	}
			
		    	
		    	
		    	
		 	    try {
		 	    	BufferedWriter writer = new BufferedWriter(new FileWriter(DataWriteLocation+"/SQLTables.txt"));
                                System.out.println(otherTables);
					writer.write(allTables);
				    writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	   
		}
		
		
		
		public static ArrayList getDatabseTabelNames(){
			ArrayList allTableNames = new ArrayList();
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/sugarcrm","root","");  
				//here sonoo is database name, root is username and password  
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SHOW TABLES FROM sugarcrm");  
				while(rs.next())  
					allTableNames.add(rs.getObject(1)); 
					con.close();  
			}catch(Exception e){ 
				System.out.println(e);
				}  
			
			return allTableNames;
		}

}
