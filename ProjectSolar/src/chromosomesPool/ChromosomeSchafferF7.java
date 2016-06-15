package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeSchafferF7 extends Chromosome {
	private static final double INFIMUM = -100;
	private static final double MAXIMUM = 100;
	private static final int NOBJECTIVES = 1;

	private ChromosomeSchafferF7(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeSchafferF7 createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeSchafferF7(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.SchafferF7(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
}
