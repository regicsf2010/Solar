package principal;

import java.util.Arrays;
import java.util.Collections;

import auxiliaries.Configuration;
import auxiliaries.Configuration.Ackley;
import comparators.FitnessAscendantComparator;
import implementations.ArithmeticCrossover;
import implementations.BestPairSurvivorSelection;
import implementations.ExchangeMigration;
import implementations.GaussianMutation;
import implementations.TournamentSelection;

public class MainSolar {
	
	public static void main(String args[]) {
		//interfaceTest();
		mainProgram(null);
	}
	
	public static void interfaceTest() {
		Population p = Population.createPopulation(Configuration.NCHROMOSOME, Ackley.ID, true);
		for (int i = 0; i < p.getSize(); i++) {
			p.getChromosome(i).evaluate();
			System.out.println(p.getChromosome(i).toString());
		}
		
		Collections.sort(Arrays.asList(p.getChromosomes()), new FitnessAscendantComparator());
		for (int i = 0; i < p.getSize(); i++) {
			System.out.println(p.getChromosome(i).toString());
		}
		
	}
	
	public static void mainProgram(String args[]) {
		/* Store new instances of the problem */ 
		Solar solars[] = new Solar[Configuration.NPOPULATION];
		Thread threads[] = new Thread[Configuration.NPOPULATION];
		
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
			threads[i] = new Thread(solars[i]);
			threads[i].start();
		}
		
		/* Start threads of operators */ 
		new Thread(new ExchangeMigration(solars)).start();
		
		/* Wait for the finish of each problem */		
		for (int i = 0; i < solars.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/* Set this variable to 'false' will finish other threads (operators) */
		Configuration.isRunning = false;
		
		/* Call scripts to store information about the performance */
		
	}

}
