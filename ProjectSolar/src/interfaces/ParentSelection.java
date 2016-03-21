package interfaces;

import abstracts.Chromosome;

public interface ParentSelection {
	
	/**
	 * This scheme returns a copy of each selected parent in the population. 
	 * @return The mating pool, i.e., an array of selected chromosomes.
	 */
	public Chromosome[] doParentSelection(Chromosome population[]);
}
