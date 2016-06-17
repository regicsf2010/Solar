package implementations;

import auxiliaries.Configuration;
import interfaces.Crossover;
import principal.Population;

public class ArithmeticCrossover implements Crossover {

	@Override
	public Population doCrossover(Population parents) {
		Population offspring = Population.createPopulation(parents.getSize(), parents.getFunction(), false);
		int nGenes = parents.getChromosome(0).getGenes().length;
		
		for (int i = 0; i < Configuration.NCHROMOSOME; i += 2) {
			for (int j = 0; j < nGenes; j++) {
				double a1 = parents.getChromosome(i).getGene(j);
				double a2 = parents.getChromosome(i + 1).getGene(j);
				
				if(Configuration.MT.nextDouble() <= Configuration.CROSSOVERRATE) {
					offspring.getChromosome(i).setGene(j, a1 + (a2 - a1) * Configuration.MT.nextDouble(true, true));
					offspring.getChromosome(i + 1).setGene(j, a2 + (a1 - a2) * Configuration.MT.nextDouble(true, true));
				} else {
					offspring.getChromosome(i).setGene(j, a1);
					offspring.getChromosome(i + 1).setGene(j, a2);
				}
				
			}
		}
		
		return offspring;
	}

}
