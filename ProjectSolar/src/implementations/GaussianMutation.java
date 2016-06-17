package implementations;

import auxiliaries.Configuration;
import interfaces.Mutation;
import principal.Population;

public class GaussianMutation implements Mutation {

	@Override
	public void doMutation(Population offspring) {
		int nGenes = offspring.getChromosome(0).getGenes().length;
		double val = 0, newVal = 0;
		
		for (int i = 0; i < offspring.getSize(); i++) {
			for (int j = 0; j < nGenes; j++) {
				
				if(Configuration.MT.nextDouble() <= Configuration.MUTATIONRATE) {
					val = offspring.getChromosome(i).getGene(j);
					newVal = val + Configuration.SD * Configuration.MT.nextGaussian();
					offspring.getChromosome(i).setGene(j, newVal);
				}
				
			}
		}
	}

}
