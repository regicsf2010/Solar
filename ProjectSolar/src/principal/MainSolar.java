package principal;

import auxiliaries.Configuration;
import auxiliaries.Configuration.Ackley;
import implementations.ArithmeticCrossover;
import implementations.TournamentSelection;

public class MainSolar {

//	public static void main(String args[]) {
//		Population p = Population.createPopulation(Configuration.NCHROMOSOME, Ackley.ID, true);
//		
//		ArithmeticCrossover ac = new ArithmeticCrossover();		
//		Population s = ac.doCrossover(p);
//		
//		
//		for (int i = 0; i < p.getSize(); i++) {
//			System.out.println(p.getChromosome(i).toString());
//		}
//		
//		System.out.println();
//		for (int i = 0; i < p.getSize(); i++) {
//			System.out.println(s.getChromosome(i).toString());
//		}
//	}
	
	public static void main(String args[]) {
		/* Start new instances of the problem */ 
		Solar solars[] = new Solar[Configuration.NPOPULATION];
		
		/* Set graphs */ 
		
		/* Start each instance of the problem
		   setting the multimodal function */
		for (int i = 0; i < solars.length; i++) {
			solars[i] = new Solar(Ackley.ID, i);
			
			/* Set interfaces */ 
			solars[i].setParentSelectionInterface(new TournamentSelection());
			solars[i].setCrossoverInterface(new ArithmeticCrossover());
			
			
			new Thread(solars[i]).start();
		}
		
		/* Start threads of operators */ 
		
		/* Call scripts to store information about the performance */		
		
	}

}