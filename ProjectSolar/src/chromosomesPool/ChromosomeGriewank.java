package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeGriewank extends Chromosome {
	private static final double INFIMUM = -600;
	private static final double MAXIMUM = 600;
	private static final int NOBJECTIVES = 1;

	private ChromosomeGriewank(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeGriewank createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeGriewank(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.Griewank(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
}
