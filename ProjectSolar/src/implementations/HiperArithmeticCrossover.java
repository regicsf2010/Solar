package implementations;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.Graph;
import auxiliaries.MersenneTwisterFast;
import interfaces.HiperCrossover;
import principal.Solar;

public class HiperArithmeticCrossover implements HiperCrossover {

	private Solar solars[] = null;
	private Graph island = null;
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	public HiperArithmeticCrossover(Solar solars[], Graph island) {
		this.solars = solars;
		this.island = island;
	}
	
	/**
	 * By reference.
	 */
	@Override
	public void doHiperCrossover(Chromosome c1, Chromosome c2) {
		// Crossover c1 and c2 (if not busy)
		if(!c1.isBusy() && !c2.isBusy()) {
			for (int i = 0; i < c1.getGenes().length; i++) {
				double a1 = c1.getGene(i);
				double a2 = c2.getGene(i);
				c1.setGene(i, a1 + (a2 - a1) * this.mt.nextDouble(true, true));
				c2.setGene(i, a2 + (a1 - a2) * this.mt.nextDouble(true, true));
			}			
		}
	}
	
	@Override
	public void run() {
		double val = -1;
		
		while(Configuration.isRunning) {
			val = this.mt.nextDouble();
			if(val <= Configuration.HIPERCROSSOVERRATE) {
				// Randomize two populations' id
				int id1 = this.mt.nextInt(Configuration.NPOPULATION);
				int adjs[] = this.island.getAdjInd(id1);
				if(adjs == null) continue;
				int id2 = adjs[this.mt.nextInt(adjs.length)];
				
				// call doHiperCrossover
				if(solars[id1].isOn() && solars[id2].isOn()) {
					// Randomize two chromosomes
					int idx_p1 = this.mt.nextInt(Configuration.NCHROMOSOME);
					int idx_p2 = this.mt.nextInt(Configuration.NCHROMOSOME);
					
					this.doHiperCrossover(solars[id1].getPopulation().getChromosome(idx_p1), 
							  			  solars[id2].getPopulation().getChromosome(idx_p2));
				}				
			}
		}
			
	}

}
