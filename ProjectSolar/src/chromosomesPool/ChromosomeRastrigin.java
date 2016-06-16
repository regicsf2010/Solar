package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeRastrigin extends Chromosome {
	private static final double INFIMUM = -40;
	private static final double MAXIMUM = 40;
	private static final int NOBJECTIVES = 1;

	private ChromosomeRastrigin(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeRastrigin createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeRastrigin(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.Rastrigin(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
	
	public static ChromosomeRastrigin createEmptyChromosome(int nGenes) {
		return new ChromosomeRastrigin(new double[nGenes], new double[NOBJECTIVES]);
	}
}
