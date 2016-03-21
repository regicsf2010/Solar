package principal;

import auxiliaries.Graph;

public class MainSolar {
	
	public static void main(String args[]) {
		Graph g = new Graph(4);
		g.insertEdge(0, 1, 1);
		g.insertEdge(1, 2, 1);
		g.insertEdge(2, 0, 1);
		g.insertEdge(2, 3, 1);
		
		int d[] = g.getAdjInd(2);
		for (int i = 0; i < d.length; i++) {
			System.out.println(d[i]);
		}
		
	}	
}
