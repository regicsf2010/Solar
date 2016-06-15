package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeSchafferN4 extends Chromosome {
	private static final double INFIMUM = -100;
	private static final double MAXIMUM = 100;
	private static final int NOBJECTIVES = 1;

	private ChromosomeSchafferN4(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeSchafferN4 createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeSchafferN4(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.SchafferN4(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
}
