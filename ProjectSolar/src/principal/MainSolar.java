package principal;

import auxiliaries.Configuration;
import auxiliaries.Configuration.Ackley;
import implementations.ArithmeticCrossover;
import implementations.BestPairSurvivorSelection;
import implementations.GaussianMutation;
import implementations.TournamentSelection;

public class MainSolar {
	
	public static void main(String args[]) {
		/* Store new instances of the problem */ 
		Solar solars[] = new Solar[Configuration.NPOPULATION];
		
		/* Set graphs */ 
		
		/* Start each instance of the problem
		   setting the multimodal function */
		for (int i = 0; i < solars.length; i++) {
			/* Start new instance of the problem */
			solars[i] = new Solar(Ackley.ID, i);
			
			/* Set interfaces */ 
			solars[i].setParentSelectionInterface(new TournamentSelection());
			solars[i].setCrossoverInterface(new ArithmeticCrossover());
			solars[i].setMutationInterface(new GaussianMutation());
			solars[i].setSurvivorSelectionInterface(new BestPairSurvivorSelection());
			
			/* Start problem as a thread */
			new Thread(solars[i]).start();
		}
		
		/* Start threads of operators */ 
		
		/* Call scripts to store information about the performance */		
		
	}

}
