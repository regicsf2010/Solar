package comparators;

import java.util.Comparator;

import abstracts.Chromosome;

public class FitnessAscendantComparator implements Comparator<Chromosome> {
	/**
	 * This method is used to sort chromosomes by fitness.
	 * The method sorts array of chromosomes in ASCENDANT order.
	 * 
	 */
	@Override
	public int compare(Chromosome c1, Chromosome c2) {
		return (c1.getFitness() < c2.getFitness())? -1 : (c1.getFitness() == c2.getFitness())? 0 : 1;
	}
}
