package com.graph.main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.graph.matricesandedges.UniqueNodesGivenGraphs;



public class IdentifySubGraphs {
	
	static List<Integer>[] arrayOfListUniqueNodesOut;
	//Here we try to identify the subgraphs for the given support upwards. 
	public static void identifySubGraphsWithSupport(int support, int noOfGraphs, ArrayList bothMatrices, List<Integer>[] arrayOfListUniqueNodes ){
		arrayOfListUniqueNodesOut = new List[arrayOfListUniqueNodes.length];
		arrayOfListUniqueNodesOut = arrayOfListUniqueNodes;
		
		int[][] matrixWithTheToatalValues = (int[][]) bothMatrices.get(0);
		String[][] matrixWithTheGraphsHavingPositions = (String[][])bothMatrices.get(1);
		support = support;
		
		ArrayList theEdges = new ArrayList();//Contains all the edges which are greater than the given support
		ArrayList supportValue = new ArrayList(); //Contains the support for each edge
		ArrayList graphHavingEdge = new ArrayList();//Contains the greaphs which contians those edges
		
		
		for(int i=0;i<matrixWithTheToatalValues.length;i++){
			for(int j=0;j<matrixWithTheToatalValues.length;j++){
				if(matrixWithTheToatalValues[i][j] >= support){
					String edge = i+"-"+j;
					String inversEdge = j+"-"+i;
					if(!theEdges.contains(edge) && !theEdges.contains(inversEdge) ){
						theEdges.add(edge);
						supportValue.add(matrixWithTheToatalValues[i][j]);
						graphHavingEdge.add(matrixWithTheGraphsHavingPositions[i][j]);
					}
				}
			}
		}
		
		
		//Converting the graphsHavingEdges to an arraylist with all the graphs
		ArrayList graphWithEdgesChanged = new ArrayList();
		for(int i=0;i<graphHavingEdge.size();i++){
			String []graphs = graphHavingEdge.get(i).toString().split(",");
			ArrayList <String>temp =  new ArrayList<String>(Arrays.asList(graphs));
			graphWithEdgesChanged.add(temp);
		}
		
		
		ArrayList uniqueGraphCombinations = new ArrayList();
		for(int i=0;i<supportValue.size();i++){
			if(!uniqueGraphCombinations.contains(graphWithEdgesChanged.get(i)) && (int)supportValue.get(i) >= support){
				uniqueGraphCombinations.add(graphWithEdgesChanged.get(i));
			}
		}
		
		
		for(int i=0;i<uniqueGraphCombinations.size();i++){
			System.out.println("Combination: "+uniqueGraphCombinations.get(i));
			ArrayList edgesInTheCombinationGraph = new ArrayList();
			for(int j=0;j<graphWithEdgesChanged.size();j++){
				if(((ArrayList)graphWithEdgesChanged.get(j)).containsAll((Collection) uniqueGraphCombinations.get(i))){
					System.out.print(theEdges.get(j)+",");
					edgesInTheCombinationGraph.add(theEdges.get(j));
				}
				
			}
			
			identifyUniqueNodes(edgesInTheCombinationGraph, uniqueGraphCombinations.get(i).toString());
			//Now we need to identify the unique nodes in the graphs
			
			
			
			
			System.out.println("");
			
		}
		
		
		
		//System.out.println("1");
	}
	
	
	
	public static void identifyUniqueNodes(ArrayList edgesInTheCombinationGraph, String graphCombinaitons){
		ArrayList allTheUniqueSubGraphs = new ArrayList();
		for(int i=0;i<edgesInTheCombinationGraph.size();i++){
			if(i==0){
				ArrayList temp = new ArrayList();
				String []nodesSplit = edgesInTheCombinationGraph.get(i).toString().split("-");
				temp.add(nodesSplit[0]);
				temp.add(nodesSplit[1]);
				allTheUniqueSubGraphs.add(temp);
			}else{
				String []nodesSplit = edgesInTheCombinationGraph.get(i).toString().split("-");
				for(int j=0;j<allTheUniqueSubGraphs.size();j++){
					ArrayList previousList = (ArrayList)allTheUniqueSubGraphs.get(j);
					
					if(previousList.contains(nodesSplit[0]) && !previousList.contains(nodesSplit[1])){
						previousList.add(nodesSplit[1]);
					}else if(previousList.contains(nodesSplit[1])&& !previousList.contains(nodesSplit[0])){
						previousList.add(nodesSplit[0]);
					}else if(!previousList.contains(nodesSplit[0])&& !previousList.contains(nodesSplit[1]) && j==allTheUniqueSubGraphs.size()-1){
						ArrayList temp = new ArrayList();
						temp.add(nodesSplit[0]);
						temp.add(nodesSplit[1]);
						allTheUniqueSubGraphs.add(temp);
					}
				}
				
			}
		}
		
		
		
		//However in the unique subgraphs we can have some ones which are connected. So we have to figure them out also.
		ArrayList finalizedSubGraphs = identifyTheUniqueSubGrpah(allTheUniqueSubGraphs);
		
		
		
		
		
		System.out.println("");
		//Now we print the unique subgraphs
		for(int i=0;i<finalizedSubGraphs.size();i++){
			ArrayList temp = (ArrayList)finalizedSubGraphs.get(i);
			
			//Here we call the average Similarity to get the similarity measure for the maximum subgraph
			averageSimilarityOfGraphs(temp, graphCombinaitons);
			
			for(int j=0;j<temp.size();j++){
				System.out.print(temp.get(j)+",");
			}
			System.out.println("");
			System.out.println("------------------------------------############---------------------------------");
		}
		
	}
	
	
	
	
	//we use this method to get the average similarity of the graphs based on probabilit
	public static void averageSimilarityOfGraphs(ArrayList subgraphNodes, String numberOfTheCombination){
		 numberOfTheCombination = numberOfTheCombination.replace("[", "");
		 numberOfTheCombination = numberOfTheCombination.replace("]", "");
		 numberOfTheCombination = numberOfTheCombination.replace(" ", "");
		 String[] splitArray = numberOfTheCombination.split(",");
		 
		//Here we ge the total number of unique nodes in the give graphs. We manually gave the values
		 //int[] countOfNodes = {140, 36, 40, 25, 40, 49, 69, 67, 73, 34, 52};
		 int[] countOfNodes = {87,44,83,48,98,48,40,74,63,31,200};
		 int similaritySize = subgraphNodes.size();
		 
		 
		 float average = 0.0f;
		 int sum = 0;
		 UniqueNodesGivenGraphs uniqueNodesGivenGraphs = new UniqueNodesGivenGraphs();
		 int uniqeNodesCount =uniqueNodesGivenGraphs.uniqueNodesforGivenGraphs(arrayOfListUniqueNodesOut, splitArray);
		 
		 
		 
		 for(int i=0;i<splitArray.length;i++){

			 sum = sum + countOfNodes[Integer.parseInt(splitArray[i])]; 
		 }
		 
		 
		 
		 float compression = (similaritySize * splitArray.length)/(float)sum;
		 
		 //average = (float)similaritySize / totalUniqueNodesCount;
		 
		 //average = average/(splitArray.length);
		 System.out.println("Average Similarity: "+(float)similaritySize/uniqeNodesCount);
		 System.out.println("Compression Ratio: "+compression);
		
	}
	
	
	
	//From the top one we get different unique nodes and subgraphs. But some subgraphs can be combined to one 
		//[The out put given in] Unique nodes
		//So here we try to handle that
		public static ArrayList identifyTheUniqueSubGrpah(ArrayList uniqueNodes){

				
				for(int i=0;i<uniqueNodes.size();i++){
					ArrayList temp = (ArrayList)uniqueNodes.get(i);
					for(int j=0;j<uniqueNodes.size();j++){
						ArrayList temp2 = (ArrayList)uniqueNodes.get(j);
						if(CollectionUtils.containsAny(temp,temp2)){
							for(int x=0;x<temp2.size();x++){
								if(!temp.contains(temp2.get(x))){
									temp.add(temp2.get(x));
								}
							}
							uniqueNodes.set(i, temp);
						}
					}
					
				}
					
				
				
//				int aa =0;
//				while(aa == 0){
//					int uniqueNodeSize = uniqueNodes.size();
//				for(int i=0;i<uniqueNodes.size();i++){
//					if(i<uniqueNodes.size()-2 && ((ArrayList)uniqueNodes.get(i)).containsAll((ArrayList)uniqueNodes.get(i+1))){
//						uniqueNodes.remove(i+1);
//						break;
//					}
//					
//					if(i==uniqueNodeSize-1 && uniqueNodeSize == uniqueNodes.size()){
//						aa =1;
//						break;
//					}
//				}
//				}
				
			
			return uniqueNodes;
		}
	
}
