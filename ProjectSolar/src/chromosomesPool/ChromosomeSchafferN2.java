package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeSchafferN2 extends Chromosome {
	private static final double INFIMUM = -4;
	private static final double MAXIMUM = 4;
	private static final int NOBJECTIVES = 1;

	private ChromosomeSchafferN2(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public ChromosomeSchafferN2() {
		super(null, null);
	}
	
	public static ChromosomeSchafferN2 createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeSchafferN2(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.SchafferN2(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}
	
	public static ChromosomeSchafferN2 createEmptyChromosome(int nGenes) {
		return new ChromosomeSchafferN2(new double[nGenes], new double[NOBJECTIVES]);
	}
}