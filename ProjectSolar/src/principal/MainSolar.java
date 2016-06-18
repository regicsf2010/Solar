package principal;

import java.util.Arrays;
import java.util.Collections;

import auxiliaries.Configuration;
import auxiliaries.Configuration.Ackley;
import comparators.FitnessAscendantComparator;
import implementations.ArithmeticCrossover;
import implementations.BestPairSurvivorSelection;
import implementations.GaussianMutation;
import implementations.TournamentSelection;

public class MainSolar {
	
	public static void main(String args[]) {
		interfaceTest();
	}
	
	public static void interfaceTest() {
		Population p = Population.createPopulation(Configuration.NCHROMOSOME, Ackley.ID, true);
		for (int i = 0; i < p.getSize(); i++) {
			p.getChromosome(i).evaluate();
			System.out.println(p.getChromosome(i).toString());
		}
		BestPairSurvivorSelection bp = new BestPairSurvivorSelection();
		Collections.sort(Arrays.asList(p.getChromosomes()), new FitnessAscendantComparator());
		for (int i = 0; i < p.getSize(); i++) {
			System.out.println(p.getChromosome(i).toString());
		}
		
	}
	
	public static void mainProgram(String args[]) {
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
