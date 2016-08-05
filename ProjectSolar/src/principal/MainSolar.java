package principal;

import java.text.DecimalFormat;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.Configuration.Griewank;
import implementations.ArithmeticCrossover;
import implementations.BestPairSurvivorSelection;
import implementations.ExchangeMigration;
import implementations.GaussianMutation;
import implementations.HiperArithmeticCrossover;
import implementations.HiperGaussianMutation;
import implementations.TournamentSelection;

public class MainSolar {
	
	public static void main(String args[]) {
		mainProgram(null);
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
			solars[i] = new Solar(Griewank.ID, i);
			
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
		new Thread(new HiperArithmeticCrossover(solars)).start();
		new Thread(new HiperGaussianMutation(solars)).start();
		
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
		
		DecimalFormat df = new DecimalFormat("#.###");
		for (int i = 0; i < threads.length; i++) {
			Population p = solars[i].getPopulation();
			Chromosome c = p.getChromosome(p.getFittest());
			c.evaluate();
			System.out.println("(" + df.format(c.getGene(0)) + ", " + df.format(c.getFitness()) + ")");
		}
		
		/* Call scripts to store information about the performance */
		
	}

}
