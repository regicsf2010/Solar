package interfaces;

import abstracts.Chromosome;

public interface Crossover {

	/**
	 * Using the concept of parameters passed by reference.
	 * The result of the crossover (offspring) is in c1 and c2 objects themselves.
	 * @param c1 First parent
	 * @param c2 Second parent
	 */
	public void doCrossover(Chromosome c1, Chromosome c2);
}
