package com.graph.matricesandedges;

import java.util.ArrayList;
import java.util.List;

//This is to get the unique nodes for the given graphs set
public class UniqueNodesGivenGraphs {
	
	public static int uniqueNodesforGivenGraphs(List<Integer>[] arrayOfListUniqueNodes , String[] graphs){
		ArrayList uniquenodes = new ArrayList();
		for(int i = 0; i < graphs.length; i++){
			int graphNeeded = Integer.parseInt(graphs[i]);
			if(i == 0){
				uniquenodes.add(arrayOfListUniqueNodes[graphNeeded]);
			}else{
				List<Integer> nodes = arrayOfListUniqueNodes[graphNeeded];
				for(int j = 0; j < nodes.size() ; j++){
					if(!uniquenodes.contains(nodes.get(j))){
						uniquenodes.add(nodes.get(j));
					}
				}
				
			}
			
		}
		
		return uniquenodes.size();
	} 
	
}
