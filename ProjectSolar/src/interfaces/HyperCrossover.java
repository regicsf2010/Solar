package interfaces;

import abstracts.Chromosome;

public interface HyperCrossover extends Runnable {

	public void doHyperCrossover(Chromosome c1, Chromosome c2);
	
}
