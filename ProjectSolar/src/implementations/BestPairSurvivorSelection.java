package implementations;

import java.util.Arrays;
import java.util.Collections;

import auxiliaries.Configuration;
import comparators.FitnessDescendantComparator;
import interfaces.SurvivorSelection;
import principal.Population;

public class BestPairSurvivorSelection implements SurvivorSelection {

	@Override
	public Population doSurvivorSelection(Population p, Population offspring) {
		Population survivor = Population.createPopulation(Configuration.NCHROMOSOME, p.getFunction(), false);
		
		Collections.sort(Arrays.asList(p.getChromosomes()), new FitnessDescendantComparator());
		Collections.sort(Arrays.asList(offspring.getChromosomes()), new FitnessDescendantComparator());
		
		for (int i = 0; i < Configuration.NCHROMOSOME; i++) {
			if(p.getChromosome(i).getFitness() > offspring.getChromosome(i).getFitness())
				survivor.setChromosome(i, p.getChromosome(i));
			else
				survivor.setChromosome(i, offspring.getChromosome(i));
		}
		
		return survivor;
	}

}
