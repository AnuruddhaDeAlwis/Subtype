package GraphMining.com.graph.frequency;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetMatrixValuesFromExcel {
	static ArrayList allTheValuesOfGivenExcelSheet = new ArrayList(); //This is to keep all the vallues of the given excel sheet.
	static ArrayList allTheStringValuesofExcelSheet = new ArrayList(); //This contains the names of the given excel sheet
	static String fileName;
        
        static String saveLocation = "";
	
public static void readExcelFiles(final File folder,ArrayList uniqueNames, String saveFolder) throws IOException{
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
						readExcelFiles(fileEntry,uniqueNames,saveFolder);
					        } else {
                                                        saveLocation = saveFolder;
					        	fileName = FilenameUtils.removeExtension(fileEntry.getName());
					        	readExcelMatrices(uniqueNames,new File(fileEntry.getAbsolutePath()));
					        	
							}
		}
		
		writingUniqueValuesToText(uniqueNames);
	}

	
	
	
	
	
	
	
	//This is created to get excel sheets matrix values
	public static void readExcelMatrices(ArrayList uniqueNames,File pathToRead){
		
		try {
			//FileInputStream file = new FileInputStream(new File("C:/Test/ANonEmail.xls"));
			FileInputStream file = new FileInputStream(pathToRead);
			
			//Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				ArrayList rowdata = new ArrayList();
				Row row = rowIterator.next();
				
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					int xx =cell.getCellType();
					if(xx == 0) {
				
						int a = (int) cell.getNumericCellValue();
						if(a >= 1){
							rowdata.add(1);
						}else{
							rowdata.add(0);
						}
						//System.out.print(cell.getNumericCellValue() + "\t\t");
						
				}else if(xx == 1){
					//String type
					if(!allTheStringValuesofExcelSheet.contains(cell.getStringCellValue())){
							allTheStringValuesofExcelSheet.add(cell.getStringCellValue());
					}
				}
					
				}
				if(rowdata.size() > 1){
					allTheValuesOfGivenExcelSheet.add(rowdata);
				}
				
				System.out.println("");
			}
			file.close();
			addMissingLinesToMatrix(uniqueNames);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//This Method is to to add the missing matrix values so that all the matrices will contian same amount of elements
	//To ge the cosine value
	public static void addMissingLinesToMatrix(ArrayList allTheNamesOfthePaths){
		

		
		for(int i=0; i<allTheNamesOfthePaths.size();i++){
			if(!allTheStringValuesofExcelSheet.contains(allTheNamesOfthePaths.get(i))){
				System.out.println("Index: "+i);
				//First add the row with zero values
				ArrayList temp = new ArrayList();
				for(int j=0;j<allTheValuesOfGivenExcelSheet.size();j++){
					temp.add(0);
				}
				allTheValuesOfGivenExcelSheet.add(i, temp);
				allTheStringValuesofExcelSheet.add(i,allTheNamesOfthePaths.get(i));
				
				
				//Now adding the values to all arraylist[Column vize add]
				for(int k=0;k<allTheValuesOfGivenExcelSheet.size();k++){
					ArrayList temparry = (ArrayList) allTheValuesOfGivenExcelSheet.get(k);
					temparry.add(i, 0);//This execution is not in that given file. so we add 0
					allTheValuesOfGivenExcelSheet.set(k, temparry);
				}
			}
		}
		
		
		
		//First we swap the values based on the indexing of the unique names[allTheNamesOfthePaths]
				//Should do after inserting all the values.
				for(int i=0;i<allTheNamesOfthePaths.size();i++){
					if(allTheStringValuesofExcelSheet.contains(allTheNamesOfthePaths.get(i)) && (allTheStringValuesofExcelSheet.indexOf(allTheNamesOfthePaths.get(i))!=i)){
						//This is handleing the rows
						int indexOftheWrongItem = allTheStringValuesofExcelSheet.indexOf(allTheNamesOfthePaths.get(i));
						allTheStringValuesofExcelSheet.remove(indexOftheWrongItem);
						allTheStringValuesofExcelSheet.add(i, allTheNamesOfthePaths.get(i));
						
						ArrayList rowInfo = (ArrayList) allTheValuesOfGivenExcelSheet.get(indexOftheWrongItem);
						allTheValuesOfGivenExcelSheet.remove(indexOftheWrongItem);
						allTheValuesOfGivenExcelSheet.add(i, rowInfo);
						
						
						//This it to handle the columns
						for(int j=0;j<allTheNamesOfthePaths.size();j++){
							ArrayList tempArray = (ArrayList) allTheValuesOfGivenExcelSheet.get(j);
							int valueOfGivenPosition = (int) tempArray.get(indexOftheWrongItem);//Get the value of the wrong Item
							tempArray.remove(indexOftheWrongItem);
							tempArray.add(i, valueOfGivenPosition);
							allTheValuesOfGivenExcelSheet.set(j, tempArray);
						}

					}
				}
		
				writingTheValuesToText(allTheValuesOfGivenExcelSheet);
		System.out.println("Done Finallly");
		
	}

	
	
	//Writing the Values to a textFile
	public static void writingTheValuesToText(ArrayList allTheIntsOfGivenExcelSheet){
		File file = new File(saveLocation+"/"+fileName+".txt");
		file.getParentFile().mkdir();
		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try{
			PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
        	
			
			for(int i=0;i<allTheIntsOfGivenExcelSheet.size();i++){
				ArrayList tempArray = (ArrayList) allTheIntsOfGivenExcelSheet.get(i);
				for(int j=0;j<tempArray.size();j++){
					writer.print(tempArray.get(j)+",");
				}
				writer.print("&\n");
			}
			 
			    writer.close();
			    allTheValuesOfGivenExcelSheet.clear();
			    allTheStringValuesofExcelSheet.clear();
			} catch (IOException e) {
			   // do something
			}
	 	   
		
	}
	
	
	
	//Saving all the unique names as a text file. [Unique classpaths and table names]
	public static void writingUniqueValuesToText(ArrayList uniqueValues){
                File file = new File(saveLocation+"/Unique.txt");
		file.getParentFile().mkdir();
		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try{
			PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
        	
			
			for(int i=0;i<uniqueValues.size();i++){
				writer.print(uniqueValues.get(i));
				writer.write(System.getProperty( "line.separator" ));
				
			}
			 
			    writer.close();
			    
			} catch (IOException e) {
			   // do something
			}
	 	   
		
	}
	
}