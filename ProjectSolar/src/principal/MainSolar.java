package principal;

import java.text.DecimalFormat;
import java.util.ArrayList;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.Configuration.Rastrigin;
import auxiliaries.Graph;
import implementations.ArithmeticCrossover;
import implementations.BestPairSurvivorSelection;
import implementations.ExchangeMigration;
import implementations.GaussianMutation;
import implementations.HyperArithmeticCrossover;
import implementations.HyperGaussianMutation;
import implementations.TournamentSelection;

public class MainSolar {
	
	public static void main(String args[]) {
		mainProgram(null);			
		
	}
	
	public static void mainProgram(String args[]) {
		/* Store new instances of the problem */ 
		Solar solars[] = new Solar[Configuration.NPOPULATION];
		Thread problems[] = new Thread[Configuration.NPOPULATION];
		
		/* Set graphs */ 		
		Graph island = new Graph(Configuration.NPOPULATION);
		island.completeGraph();
		
		/* Start each instance of the problem
		   setting the multimodal function */
		for (int i = 0; i < solars.length; i++) {
			/* Start new instance of the problem */
			solars[i] = new Solar(Rastrigin.ID, i);
			
			/* Set interfaces */ 
			solars[i].setParentSelectionInterface(new TournamentSelection());
			solars[i].setCrossoverInterface(new ArithmeticCrossover());
			solars[i].setMutationInterface(new GaussianMutation());
			solars[i].setSurvivorSelectionInterface(new BestPairSurvivorSelection());
			
			/* Start problem as a thread */
			problems[i] = new Thread(solars[i]);
			problems[i].start();
		}
		
		/* Start threads of operators */
		ArrayList<Thread> operators = new ArrayList<Thread>();
		operators.add(new Thread(new ExchangeMigration(solars, island)));
		operators.add(new Thread(new HyperArithmeticCrossover(solars, island)));
		operators.add(new Thread(new HyperGaussianMutation(solars)));
		for (Thread t : operators) 
			t.start();
		
		
		/* Wait for the finish of each problem */		
		for (int i = 0; i < solars.length; i++) {
			try {
				problems[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/* Set this variable to 'false' will finish other threads (operators) */
		Configuration.isRunning = false;
		
		/* Wait for the finish of each operator */
		for (int i = 0; i < operators.size(); i++) {
			try {
				operators.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("\nBest result of each population:");
		DecimalFormat df = new DecimalFormat("#.###");
		for (int i = 0; i < problems.length; i++) {
			Population p = solars[i].getPopulation();
			Chromosome c = p.getChromosome(p.getFittest());
			c.evaluate();
			
			System.out.print("(");
			for (int j = 0; j < c.getGenes().length; j++) {
				System.out.print(df.format(c.getGene(j)));
				if(j != c.getGenes().length - 1)
					System.out.print(", ");
			}
			System.out.println(") | f(x) = " + df.format(c.getFitness()));		
		}
		
		/* Call scripts to store information about the performance */
		
	}

}
