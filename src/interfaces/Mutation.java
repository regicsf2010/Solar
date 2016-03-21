package interfaces;

import abstracts.Chromosome;

public interface Mutation {
	
	/**
	 * Using the concept of parameters passed by reference.
	 * The result of the mutation is in c object itself.
	 * @param c Chromosome
	 */
	public void doMutation(Chromosome c);
}
