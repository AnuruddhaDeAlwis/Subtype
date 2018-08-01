package GraphMining.com.graph.matricesandedges;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ReadingMatrices {
	//This calss is resposible for reading all the matrices in the given txt file and then make their diagonal values zero
	//and make them undirected graphs
	
	
	//Reading the data from the files
		 public static int[][][] listFilesForFolder(final File folder) throws Exception {
			 int squareMatrixSize = 0; //To define each graphs matrix size
			 int support = 0;//We need subgraphs which are in all these graphs.
	    	 int adjacency_matrix_inside[][][] =  new int[0][0][0];// This contains all the graphs in the given txt file
			 
			    for (final File fileEntry : folder.listFiles()) {
			        if (fileEntry.isDirectory()) {
			            listFilesForFolder(fileEntry);
			        } else {
			            
			           
			            String ext1 = FilenameUtils.getExtension(fileEntry.getAbsolutePath());
			            if(ext1.equalsIgnoreCase("txt")){
			            	String aa = FileUtils.readFileToString(
			            			new File(fileEntry.getAbsolutePath()), "UTF-8");

			            	
			            	
			            	String uniqueGraphs[] = aa.split("&#");
			            	
			            	
			            	//support = uniqueGraphs.length; 
			            	support = 3;
			            	
			            	// So here we will have an adjacency matrix for one graph. Through the outer loop we can go through 
			            	//[i for loop] all graphs in the given txt file
			            	for(int i=0;i<uniqueGraphs.length;i++){
			            		
			            		//Here we take each individual graph and process it.
			            		String individualGraph[] =  uniqueGraphs[i].split("&");
                                                System.out.println("Graph length: "+individualGraph.length);
			            		
			            		//Now we have to create the adjacency matrix for each graph, and it should be an int.
			            		if(adjacency_matrix_inside.length == 0 ){
			            			System.out.println("Inside");
			            			adjacency_matrix_inside = new int[uniqueGraphs.length][individualGraph.length][individualGraph.length];
			            		}
			            		
			            		for(int j=0;j<individualGraph.length;j++){
			            			String individualLine[] = individualGraph[j].split(",");
			            			squareMatrixSize = individualLine.length;
			            			for(int k=0;k<individualLine.length;k++){
			            				if(individualLine[k].equalsIgnoreCase( "0")){
			            					adjacency_matrix_inside[i][j][k] = 0;
			            				}else{
			            					adjacency_matrix_inside[i][j][k] = 1;
			            				}
			            				
			            			}
			            			
			            		}
			            		
			            		
			            		
			            		
			            	}
			            	
			            	
			            	
			           
			        }
			            
			    }
			}
			    
			    
			return graphProcessing(adjacency_matrix_inside, support, squareMatrixSize);
			    
			    
		 } 
		 
		 
		 
		 public static int[][][] graphProcessing(int[][][] adjacency_matrices, int support, int squareMatrixSize){
			 
				//squareMatrixSize ==  [This is the number of nodes in the matrix]
					
				 //Dynamically create the nodes to pass into the methods we need.
				 Map<String, Node> map = new HashMap<>();
				 for(int i = 0; i <squareMatrixSize; i++) {
				     map.put("node" + (i+1), new Node(i+1));
				 }
				 
				 //adjacency_matrices = makeMatrixUndirected(makeDiagonalValuesZero(adjacency_matrices));
				 System.out.print("TT");
				 return adjacency_matrices;
				 
		 }
		 
		 
		 
		//This to make sure that our diagonal values are zero so we don't have loops among it self. Cycle from A-A
			public static int[][][] makeDiagonalValuesZero(int[][][] matrixGiven){
				
				// Since this is a square matrix we can do this. [length ==  height]
				for(int a=0;a<matrixGiven.length;a++){
					int matrixSize = matrixGiven[a].length;
					for(int i=0;i<matrixSize;i++){
						matrixGiven[a][i][i] = 0;
					}
					
				}
				
				
				return matrixGiven;
			}
			
			
			
			//This is to make sure that our matrix undirected. If AB is 1 then BA should also be 1
			public static int[][][] makeMatrixUndirected(int[][][] matrixGiven){
				
				for(int a=0;a<matrixGiven.length;a++){
					int matrixSize = matrixGiven[a].length;
					
					for(int i=0;i<matrixSize;i++){
						//Since this is a square matrix length ==  height
						for(int j =0;j<matrixSize;j++){
							if(matrixGiven[a][i][j] == 1){
								matrixGiven[a][j][i] = 1;
							}
						}
						
					}
				}
				
				
				
				
				return matrixGiven;
			}
}
