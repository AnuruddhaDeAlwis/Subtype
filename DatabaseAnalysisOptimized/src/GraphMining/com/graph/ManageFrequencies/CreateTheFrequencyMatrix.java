package GraphMining.com.graph.ManageFrequencies;

public class CreateTheFrequencyMatrix {
	
	public static int[][] getTheFrequencyMatrix(int[][][] adjacency_matrices ){
			
		int [][]frequencyMatrix = adjacency_matrices[0];
		
		for(int i=1;i<adjacency_matrices.length;i++){	
			int [][]temp = adjacency_matrices[i];
			
			 for(int j=0;j<temp.length;j++){
				 for(int k=0;k<temp.length;k++){
					 frequencyMatrix[j][k] = frequencyMatrix[j][k]+temp[j][k];
				 }
			 }
			
		}
		
		return frequencyMatrix;
		
		
	}

}
