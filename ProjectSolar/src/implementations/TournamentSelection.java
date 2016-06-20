package implementations;

import auxiliaries.Configuration;
import auxiliaries.MersenneTwisterFast;
import interfaces.ParentSelection;
import principal.Population;

public class TournamentSelection implements ParentSelection {
	private final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	@Override
	public Population doParentSelection(Population p) {
		// Clone just for convenience
		Population selected = Population.createPopulation(p.getSize(), p.getFunction(), false);
		int idxSorted = -1, idxAux = -1;
		
		for (int i = 0; i < Configuration.NCHROMOSOME; i++) {
			idxSorted = this.mt.nextInt(p.getSize());
			
			for (int j = 1; j < Configuration.RANK; j++) { // j starts at 1
				idxAux = this.mt.nextInt(p.getSize());
				if(p.getChromosome(idxSorted).getFitness() >  p.getChromosome(idxAux).getFitness())
					idxSorted = idxAux;
			}
			
			selected.setChromosome(i, p.getChromosome(idxSorted));
		}
		
		return selected;
	}

}
