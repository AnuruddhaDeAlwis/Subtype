package GraphMining.com.graph.frequency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReadAllTheTextFilesandCreateOneFile {

	static String allTheText = "";
	static int fileCount = 0;
	static int normalCount = 0;
	
	public static void readingTheTextFiles(String fileLocation, String saveLocation){
		final File folder = new File(fileLocation);
        try {
        	listFilesForFolderCount(folder);
			listFilesForFolder(folder);
			
			File myFile = new File(saveLocation+"/totalGraphs.txt");

            if(!myFile.exists()) {
                myFile.createNewFile(); //creating it
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveLocation+"/totalGraphs.txt"));
            writer.write(allTheText);
             
            writer.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  public static void listFilesForFolder(final File folder) throws Exception {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		            listFilesForFolder(fileEntry);
		        } else {
		            
		           
		            String ext1 = FilenameUtils.getExtension(fileEntry.getAbsolutePath());
		            if(ext1.equalsIgnoreCase("txt")){
		            	String aa = FileUtils.readFileToString(
		            			new File(fileEntry.getAbsolutePath()), "UTF-8");
		            	
		            				aa = aa.replace("\n", "").replace("\r", "");
		            				if(normalCount<fileCount-1){
		            					aa = aa+"#";
		            					normalCount++;
		            				}
		            				allTheText = allTheText + aa;
		            	
		            	
		            	}
		            
		            
		            
		        }
		    }
		}
	  
	  
	  
	  
	  public static void listFilesForFolderCount(final File folder) throws Exception {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	listFilesForFolderCount(fileEntry);
		        } else {
		            fileCount++;
		           
		            	}
		            
		            
		            
		        }
		    
		}

	
}
