package principal;

import auxiliaries.Configuration;
import auxiliaries.Configuration.Ackley;

public class MainSolar {
	
	public static void main(String args[]) {
		/* Start new instances of he problem */
		Solar solars[] = new Solar[Configuration.nPopulation];
		
		/* Set interfaces */
		
		/* Set graphs */
		
		/* Start each instance of the problem
		   setting the multimodal function */
		for (int i = 0; i < solars.length; i++) {
			solars[i] = new Solar(Ackley.ID, i);
			new Thread(solars[i]).start();
		}
		
		/* Start threads of operators */
		
		/* Call scripts to store information about the performance */
		
		
	}
}


