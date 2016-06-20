package implementations;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import auxiliaries.MersenneTwisterFast;
import interfaces.Migration;
import principal.Population;
import principal.Solar;

public class ExchangeMigration implements Migration {
	
	private Solar solars[] = null;
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	public ExchangeMigration(Solar solars[]) {
		this.solars = solars;
	}
	
	@Override
	public void doMigration(Population p1, Population p2) {
		// Randomize two chromosomes
		int idx_p1 = this.mt.nextInt(Configuration.NCHROMOSOME);
		int idx_p2 = this.mt.nextInt(Configuration.NCHROMOSOME);
		
		// Swap them between p1 and p2 (if not busy)
		if(!p1.getChromosome(idx_p1).isBusy() && !p2.getChromosome(idx_p2).isBusy()) {
			Chromosome aux = Chromosome.copyChromosome(p1.getChromosome(idx_p1));
			p1.setChromosome(idx_p1, p2.getChromosome(idx_p2));
			p2.setChromosome(idx_p2, aux);
		}
	}
	
	@Override
	public void run() {
		double val = -1;
		
		while(Configuration.isRunning) {
			val = this.mt.nextDouble();
			if(val < Configuration.MIGRATIONRATE) {
				// Randomize two populations' id
				int id1 = this.mt.nextInt(Configuration.NPOPULATION);
				int id2 = this.mt.nextInt(Configuration.NPOPULATION);
				
				// call doMigration
				if(solars[id1].isOn() && solars[id2].isOn())
					this.doMigration(solars[id1].getPopulation(), solars[id2].getPopulation());
			}
		}
		System.out.println("Migration operator finished");
	}

}
