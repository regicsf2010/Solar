package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeRosenbrock extends Chromosome {
	private static final double INFIMUM = -200;
	private static final double MAXIMUM = 200;
	private static final int NOBJECTIVES = 1;

	private ChromosomeRosenbrock(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeRosenbrock createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeRosenbrock(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.Rosenbrock(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
}
