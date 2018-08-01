package GraphMining.com.graph.frequency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.ArrayList;
import java.util.Iterator;




public class ExcelSheetProcessing {
		static ArrayList uniequeName = new ArrayList();

	
	public static ArrayList readExcelFiles(final File folder) throws IOException{
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
						readExcelFiles(fileEntry);
					        } else {
					        	
					        		FileInputStream fsIP= new FileInputStream(new File(fileEntry.getAbsolutePath())); 
					        		//Read the spreadsheet that needs to be updated
					                HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
					                HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
					                Iterator<Row> iterator = worksheet.iterator();
					                
					                int ctr = 1;
					                Row row = null;
					        		Cell cell = null;
					        		boolean isNull = false;
					        		do{
					        			try{
					        			row = worksheet.getRow(ctr);
					        			cell = row.getCell(0);
					        			//System.out.println(cell.getStringCellValue());
					        			if(!uniequeName.contains(cell.getStringCellValue())){
					        				uniequeName.add(cell.getStringCellValue());
					        			}
					        			ctr++;
					        			} catch(Exception e) {
					        				isNull = true;
					        			}
					        			
					        		}while(isNull!=true);
					        		fsIP.close();
					        		System.out.println("Done");
									}
							
							
							}
				return uniequeName;
			}

					        

}
