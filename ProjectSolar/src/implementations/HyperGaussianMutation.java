package implementations;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.MersenneTwisterFast;
import interfaces.HyperMutation;
import principal.Solar;

public class HyperGaussianMutation implements HyperMutation {

	private Solar solars[] = null;
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	private boolean migrationOk;
	
	public HyperGaussianMutation(Solar solars[]) {
		this.solars = solars;
		this.migrationOk = false;
	}
	
	@Override
	public void doHyperMutation(Chromosome c) {
		// Mutation c1 (if not busy)
		if(!c.isBusy()) {
			double val = 0, newVal = 0;
			for (int i = 0; i < c.getGenes().length; i++) {
				val = c.getGene(i);
				newVal = val + Configuration.SD * this.mt.nextGaussian();
				c.setGene(i, newVal);
			}
			this.migrationOk = true; // update this information to the whole class
		}
	}

	@Override
	public void run() {
		double val = -1;
		
		while(Configuration.isRunning) {
			val = this.mt.nextDouble();
			if(val <= Configuration.HIPERMUTATIONRATE) {
				do {
					// Randomize one populations' id
					int id = this.mt.nextInt(Configuration.NPOPULATION);
	
					// call doHiperCrossover
					if(solars[id].isOn()) {
						// Randomize one chromosome
						int idx = this.mt.nextInt(Configuration.NCHROMOSOME);
						this.doHyperMutation(solars[id].getPopulation().getChromosome(idx));
					}
				} while (!this.migrationOk && Configuration.isRunning);
				this.migrationOk = false; // start everything again
			}
		}
	}	
}
