package interfaces;

import abstracts.Chromosome;

public interface SurvivorSelection {
	/**
	 * This scheme returns a copy of each survivor between current offspring and population. 
	 * @return The survivors chromosomes, i.e., an array of chromosomes.
	 */
	public Chromosome[] doSurvivorSelection(Chromosome population[]);
}
