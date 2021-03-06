package auxiliaries;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public boolean hasEdge(int v1, int v2) {
		if (this.mat[v1][v2] != 0)
			return true;
		return false;
	}

	public int[] getAdjInd(int v) {
		// Do not know the number of edges connected to v
		// That's why the use of a List
		List<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < mat.length; i++)
			if (this.mat[v][i] != 0)
				aux.add(i);
		// Copy the index of the vertices connected to v
		int values[] = null;
		if(!aux.isEmpty()) {
			values = new int[aux.size()];
			for (int i = 0; i < aux.size(); i++)
				values[i] = aux.get(i).intValue();
		}
		return values;
	}

	/**
	 * Create a graph through a string pattern. Pattern must be:
	 * (v1,v2)[w1](v3,v4)[w2]...
	 * 
	 * @param graphPattern
	 *            The pattern to be evaluated
	 * @return A graph corresponding to the pattern
	 */
	public static Graph createGraphFromPattern(String graphPattern) {
		Graph g = new Graph(Configuration.NPOPULATION);
		Pattern r = Pattern.compile(Configuration.pattern);
		Matcher m = r.matcher(graphPattern);
		while (m.find()) {
			String aux[] = m.group(1).split(",");
			g.insertEdge(Integer.valueOf(aux[0]), Integer.valueOf(aux[1]), 
					     (m.group(3) != null && Integer.valueOf(m.group(3)) != 0)? Integer.valueOf(m.group(3)):1);
		}
		return g;
	}

	public void setAutoAssociation(boolean value) {
		int c = value ? 1 : 0;
		for (int i = 0; i < this.mat.length; i++)
			this.mat[i][i] = c;
	}
	
	public void completeGraph() {
		for (int i = 0; i < this.mat.length; i++) 
			for (int j = 0; j < this.mat.length; j++) 
				this.mat[i][j] = 1;		
	}

	/**
	 * For output purpose.
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.###");
		String output = String.format("%9s", " "); // initial space (first line)
		for (int i = 0; i < this.mat.length; i++)  // write the top vertexes number
			output += String.format("%6d ", i);
		output += "\n";
		for (int i = 0; i < this.mat.length; i++) {// write each line (adjacencies)
			output += String.format("%6d-->", i);
			for (int j = 0; j < this.mat.length; j++)
				output += String.format("%6s ", df.format(this.mat[i][j]));
			output += "\n";
		}
		return output;
	}
}
