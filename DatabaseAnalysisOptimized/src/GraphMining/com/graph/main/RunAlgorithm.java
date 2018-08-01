package GraphMining.com.graph.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import GraphMining.com.graph.ManageFrequencies.CreateTheFrequencyMatrix;
import GraphMining.com.graph.ManageFrequencies.GetMatrixValuesWithFrequencies;
import GraphMining.com.graph.frequency.ExcelSheetProcessing;
import GraphMining.com.graph.frequency.GetMatrixValuesFromExcel;
import GraphMining.com.graph.frequency.ReadAllTheTextFilesandCreateOneFile;
import GraphMining.com.graph.matricesandedges.GeneratringMatricesWithData;
import GraphMining.com.graph.matricesandedges.ReadingMatrices;
import GraphMining.com.graph.matricesandedges.UniqueNodes;




public class RunAlgorithm {

	public static void main(String[] args) throws Exception {
		
//		final File folder = new File("C:/Test/");
//		ExcelSheetProcessing exProcessing = new ExcelSheetProcessing();
//		ArrayList uniqueNames = exProcessing.readExcelFiles(folder);
//		
//		GetMatrixValuesFromExcel getMatrixValues = new GetMatrixValuesFromExcel();
//		getMatrixValues.readExcelFiles(folder,uniqueNames); 
		ReadAllTheTextFilesandCreateOneFile.readingTheTextFiles("C:/Test_files/", "C:/Final_graph/");
		
		
		//GetMatrixValuesWithFrequencies.readExcelFiles(folder, uniqueNames);
		final File folder = new File("C:/Final_graph/");
		ReadingMatrices readingMatrices = new ReadingMatrices();
		
		//This contains all the adjacency matrice wjhoes diagonal values are zero and also undirected
		int[][][] adjacencyMarix = readingMatrices.listFilesForFolder(folder);
		CreateTheFrequencyMatrix.getTheFrequencyMatrix(adjacencyMarix);
		
		
//		// TODO Auto-generated method stub
//		long startTime = System.currentTimeMillis();
//		final File folder = new File("C:/Final_graph/");
//		ReadingMatrices readingMatrices = new ReadingMatrices();
//		
//		//This contains all the adjacency matrice wjhoes diagonal values are zero and also undirected
//		int[][][] adjacencyMarix = readingMatrices.listFilesForFolder(folder);
//		
//		
//		
//		
//		//Generating the two matrices we need one containing the positions and the other contianing the number of repetitions
//		GeneratringMatricesWithData genrateMatrices = new GeneratringMatricesWithData();
//		ArrayList bothMatrices =genrateMatrices.processingMatrices(adjacencyMarix);
//		
//		
//		//identification of unique node in the graphs
//		UniqueNodes uniqueNodes = new UniqueNodes();
//		List<Integer>[] arrayOfListUniqueNodes = uniqueNodes.allNodesMatrix(adjacencyMarix);
//		
//		
//		IdentifySubGraphs identifySubGraphs = new IdentifySubGraphs();
//		identifySubGraphs.identifySubGraphsWithSupport(2, 10, bothMatrices, arrayOfListUniqueNodes);
//	
//		long endTime   = System.currentTimeMillis();
//		long totalTime = endTime - startTime;
//		System.out.println("Time in millisecond: "+totalTime);
		
	}
}
