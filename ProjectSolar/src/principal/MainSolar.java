package principal;

import auxiliaries.Configuration;

public class MainSolar {
	
	public static void main(String args[]) {
		Population p = Population.createPopulation(Configuration.nChromosome, Configuration.ACKLEY);
		for (int i = 0; i < Configuration.nGenes; i++) {
			System.out.println(p.getChromosome(0).getGene(i));
		}
		
	}
}
