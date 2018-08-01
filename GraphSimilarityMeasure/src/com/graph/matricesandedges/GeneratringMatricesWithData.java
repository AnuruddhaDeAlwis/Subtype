package com.graph.matricesandedges;

import java.util.ArrayList;

public class GeneratringMatricesWithData {
	//This class is responsible for creating two matrices.
	//1. The first matrice is the one which have the collection of all the values with the given matrices.
	//2. The second matrice is the one which contains the information about the graphs which have that particule edge.
	
	public static ArrayList processingMatrices(int[][][] adjacency_matrices){
		int[][] matrixWithTheToatalValues = new int[adjacency_matrices[0].length][adjacency_matrices[0].length];
		String[][] matrixWithTheGraphsHavingPositions = new String[adjacency_matrices[0].length][adjacency_matrices[0].length];
		
		for(int i=0;i<adjacency_matrices.length;i++){
			int [][]temp = adjacency_matrices[i];
			 for(int j=0;j<temp.length;j++){
				 for(int k=0;k<temp.length;k++){
					 matrixWithTheToatalValues[j][k] = matrixWithTheToatalValues[j][k]+temp[j][k];
					 if(temp[j][k]==1 && matrixWithTheGraphsHavingPositions[j][k] == null ){
						 matrixWithTheGraphsHavingPositions[j][k] = Integer.toString(i);
					 }else if(temp[j][k]==1){
						 matrixWithTheGraphsHavingPositions[j][k] = matrixWithTheGraphsHavingPositions[j][k].concat(","+i);
					 }
				 }
			 }
			
		}
		
		ArrayList bothMatrices = new ArrayList();
		bothMatrices.add(matrixWithTheToatalValues);
		bothMatrices.add(matrixWithTheGraphsHavingPositions);
		
		return bothMatrices;
	
	}
}
