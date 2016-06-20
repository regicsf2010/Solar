package implementations;

import auxiliaries.Configuration;
import auxiliaries.MersenneTwisterFast;
import interfaces.Mutation;
import principal.Population;

public class GaussianMutation implements Mutation {
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	@Override
	public void doMutation(Population offspring) {
		int nGenes = offspring.getChromosome(0).getGenes().length;
		double val = 0, newVal = 0;
		
		for (int i = 0; i < Configuration.NCHROMOSOME; i++) {
			for (int j = 0; j < nGenes; j++) {
				
				if(this.mt.nextDouble() <= Configuration.MUTATIONRATE) {
					val = offspring.getChromosome(i).getGene(j);
					newVal = val + Configuration.SD * this.mt.nextGaussian();
					offspring.getChromosome(i).setGene(j, newVal);
				}
				
			}
		}
	}

}
