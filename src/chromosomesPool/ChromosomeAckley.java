package chromosomesPool;
import abstracts.Chromosome;

import auxiliaries.Configuration;
import problems.MathFunctions;

public class ChromosomeAckley extends Chromosome {
	private static final double infimum = -40;
	private static final double maximum = 40;
	private static final int nObjectives = 1;

	private ChromosomeAckley(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeAckley createChromosome(int nGenes) {
		double genes[] = new double[nGenes]; double val = 0;
		for (int i = 0; i < genes.length; i++) {
			val = Configuration.mt.nextDouble(true, true); // inclusive [0, 1]
			genes[i] = (1 - val) * infimum + val * maximum;
		}
		return new ChromosomeAckley(genes, new double[nObjectives]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.Ackley(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
}
