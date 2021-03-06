package implementations;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.Graph;
import auxiliaries.MersenneTwisterFast;
import interfaces.Migration;
import principal.Population;
import principal.Solar;

public class ExchangeMigration implements Migration {
	
	private Solar solars[] = null;
	private Graph island = null;
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	private boolean migrationOk;
	
	public ExchangeMigration(Solar solars[], Graph island) {
		this.solars = solars;
		this.island = island;
		this.migrationOk = false;
	}
	
	@Override
	public void doMigration(Population p1, Population p2) {
		// Randomize two chromosomes
		int idx_p1 = this.mt.nextInt(Configuration.NCHROMOSOME);
		int idx_p2 = this.mt.nextInt(Configuration.NCHROMOSOME);
		
		// Swap them between p1 and p2 (if not busy)
		if(!p1.isBusy() && !p2.isBusy()) {
			if(!p1.getChromosome(idx_p1).isBusy() && !p2.getChromosome(idx_p2).isBusy()) {
				Chromosome aux = Chromosome.copyChromosome(p1.getChromosome(idx_p1));
				p1.setChromosome(idx_p1, p2.getChromosome(idx_p2));
				p2.setChromosome(idx_p2, aux);
				this.migrationOk = true; // update this information to the whole class
			}
		}
	}
	
	@Override
	public void run() {
		double val = -1;
		
		while(Configuration.isRunning) {
			val = this.mt.nextDouble();
			if(val <= Configuration.MIGRATIONRATE) {				
				do {
					// Randomize two populations' id
					int id1 = this.mt.nextInt(Configuration.NPOPULATION);
					int adjs[] = this.island.getAdjInd(id1);
					if(adjs == null) continue; // case of isolated population
					int id2 = adjs[this.mt.nextInt(adjs.length)];
					
					// call doMigration
					if(solars[id1].isOn() && solars[id2].isOn())						
						this.doMigration(solars[id1].getPopulation(), solars[id2].getPopulation());					

					//WARNING: 
					// it must be evaluated 'Configuration.isRunning' here, otherwise 
					// this thread might in "ad eternum" loop
				} while (!this.migrationOk && Configuration.isRunning);
				this.migrationOk = false; // start everything again
			}
		}		
	}
}
