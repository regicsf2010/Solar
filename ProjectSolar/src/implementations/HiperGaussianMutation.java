package implementations;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.MersenneTwisterFast;
import interfaces.HiperMutation;
import principal.Solar;

public class HiperGaussianMutation implements HiperMutation {

	private Solar solars[] = null;
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	public HiperGaussianMutation(Solar solars[]) {
		this.solars = solars;
	}
	
	public HiperGaussianMutation(){
		
	}
	
	@Override
	public void doHiperMutation(Chromosome c) {
		// Mutation c1 (if not busy)
		if(!c.isBusy()) {
			double val = 0, newVal = 0;
			for (int i = 0; i < c.getGenes().length; i++) {
				val = c.getGene(i);
				newVal = val + Configuration.SD * this.mt.nextGaussian();
				c.setGene(i, newVal);
			}
		}
	}

	@Override
	public void run() {
		double val = -1;
		
		while(Configuration.isRunning) {
			val = this.mt.nextDouble();
			if(val <= Configuration.HIPERMUTATIONRATE) {
				// Randomize one populations' id
				int id = this.mt.nextInt(Configuration.NPOPULATION);

				// call doHiperCrossover
				if(solars[id].isOn()) {
					// Randomize one chromosome
					int idx = this.mt.nextInt(Configuration.NCHROMOSOME);
					this.doHiperMutation(solars[id].getPopulation().getChromosome(idx));
				}
			}
		}

	}

	
}
