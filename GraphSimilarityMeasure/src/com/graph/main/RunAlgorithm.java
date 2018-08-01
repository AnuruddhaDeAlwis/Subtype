package com.graph.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.graph.matricesandedges.GeneratringMatricesWithData;
import com.graph.matricesandedges.ReadingMatrices;
import com.graph.matricesandedges.UniqueNodes;



public class RunAlgorithm {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		final File folder = new File("C:/Test/");
		ReadingMatrices readingMatrices = new ReadingMatrices();
		
		//This contains all the adjacency matrice wjhoes diagonal values are zero and also undirected
		int[][][] adjacencyMarix = readingMatrices.listFilesForFolder(folder);
		
		
		
		
		//Generating the two matrices we need one containing the positions and the other contianing the number of repetitions
		GeneratringMatricesWithData genrateMatrices = new GeneratringMatricesWithData();
		ArrayList bothMatrices =genrateMatrices.processingMatrices(adjacencyMarix);
		
		
		//identification of unique node in the graphs
		UniqueNodes uniqueNodes = new UniqueNodes();
		List<Integer>[] arrayOfListUniqueNodes = uniqueNodes.allNodesMatrix(adjacencyMarix);
		
		
		IdentifySubGraphs identifySubGraphs = new IdentifySubGraphs();
		identifySubGraphs.identifySubGraphsWithSupport(2, 10, bothMatrices, arrayOfListUniqueNodes);
	
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time in millisecond: "+totalTime);
		
	}
}
