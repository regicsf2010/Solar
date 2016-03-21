package auxiliaries;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private int nVertives;
	// Using adjacent matrix
	private double mat[][];	
	
	public Graph(int nVertices) {
		this.nVertives = nVertices;
		this.mat = new double[nVertices][nVertices];
		for (int i = 0; i < this.nVertives; i++) 
			for (int j = 0; j < this.nVertives; j++) 
				mat[i][j] = 0;	
	}
	
	/**
	 * Using undirected graph.
	 */
	public void insertEdge(int v1, int v2, double value) {
		this.mat[v1][v2] = value;
		this.mat[v2][v1] = value;
	}
	
	public void removeEdge(int v1, int v2) {
		this.mat[v1][v2] = 0;
		this.mat[v2][v1] = 0;
	}
	
	public boolean isEdge(int v1, int v2) {
		if(this.mat[v1][v2] > 0)
			return true;
		return false;
	}
	
	public int[] getAdjInd(int v) {
		// Do not know the number of edges connected to v
		// That's why the use of a List
		List<Integer> aux = new ArrayList<Integer>(); 		
		for (int i = 0; i < mat.length; i++)
			if(this.mat[v][i] > 0)
				aux.add(i);
		// Copy the index of the vertices connected to v
		int values[] = new int[aux.size()];
		for (int i = 0; i < aux.size(); i++)
			values[i] = aux.get(i).intValue();
		
		return values;		
	}
}
