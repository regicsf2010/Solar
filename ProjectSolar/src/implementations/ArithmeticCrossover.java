package implementations;

import auxiliaries.Configuration;
import auxiliaries.MersenneTwisterFast;
import interfaces.Crossover;
import principal.Population;

public class ArithmeticCrossover implements Crossover {
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	@Override
	public Population doCrossover(Population parents) {
		Population offspring = Population.createPopulation(parents.getSize(), parents.getFunction(), false);
		int nGenes = parents.getChromosome(0).getGenes().length;
		
		for (int i = 0; i < Configuration.NCHROMOSOME; i += 2) {
			for (int j = 0; j < nGenes; j++) {
				double a1 = parents.getChromosome(i).getGene(j);
				double a2 = parents.getChromosome(i + 1).getGene(j);
				
				if(this.mt.nextDouble() <= Configuration.CROSSOVERRATE) {
					offspring.getChromosome(i).setGene(j, a1 + (a2 - a1) * this.mt.nextDouble(true, true));
					offspring.getChromosome(i + 1).setGene(j, a2 + (a1 - a2) * this.mt.nextDouble(true, true));
				} else {
					offspring.getChromosome(i).setGene(j, a1);
					offspring.getChromosome(i + 1).setGene(j, a2);
				}
				
			}
		}
		
		return offspring;
	}

}
