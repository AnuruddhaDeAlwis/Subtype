package com.graph.matricesandedges;
//this is to identify all the nodes related ot each graph.

import java.util.ArrayList;
import java.util.List;

public class UniqueNodes{

	public static List<Integer>[] allNodesMatrix(int[][][] adjacency_matrices){
		List<Integer>[] arrayOfList = new List[adjacency_matrices.length];
		
		for(int i=0;i<adjacency_matrices.length;i++){
				int [][]temp = adjacency_matrices[i];
				ArrayList graphNodes = new ArrayList();
				for(int j=0;j<temp.length;j++){
					 for(int k=0;k<temp.length;k++){
						 if(temp[j][k] == 1 && !graphNodes.contains(k) ){
							 graphNodes.add(k);
						 }
					 }
					}
				arrayOfList[i] = graphNodes;
		}
		
		
		
		return arrayOfList;
	}
	
}
