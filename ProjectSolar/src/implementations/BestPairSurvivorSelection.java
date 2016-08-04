package implementations;

import java.util.Arrays;
import java.util.Collections;

import auxiliaries.Configuration;
import comparators.FitnessAscendantComparator;
import interfaces.SurvivorSelection;
import principal.Population;

public class BestPairSurvivorSelection implements SurvivorSelection {

	@Override
	public Population doSurvivorSelection(Population p, Population offspring) {
		Population survivor = Population.createPopulation(Configuration.NCHROMOSOME, p.getFunction(), false);
		
		p.setBusy(true);
		Collections.sort(Arrays.asList(p.getChromosomes()), new FitnessAscendantComparator());
		p.setBusy(false);
		offspring.setBusy(true);
		Collections.sort(Arrays.asList(offspring.getChromosomes()), new FitnessAscendantComparator());
		offspring.setBusy(false);
		
		for (int i = 0; i < Configuration.NCHROMOSOME; i++) {
			p.getChromosome(i).setBusy(true);
			
			if(p.getChromosome(i).getFitness() < offspring.getChromosome(i).getFitness())
				survivor.setChromosome(i, p.getChromosome(i));
			else
				survivor.setChromosome(i, offspring.getChromosome(i));
			
			p.getChromosome(i).setBusy(false);
		}
		
		return survivor;
	}

}
