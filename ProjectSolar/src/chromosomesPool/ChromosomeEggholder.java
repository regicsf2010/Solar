package chromosomesPool;

import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeEggholder extends Chromosome {
	private static final double INFIMUM = -512;
	private static final double MAXIMUM = 512;
	private static final int NOBJECTIVES = 1;

	private ChromosomeEggholder(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public ChromosomeEggholder() {
		super(null, null);
	}
	
	public static ChromosomeEggholder createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeEggholder(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.Eggholder(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).		
	}
	
	public static ChromosomeEggholder createEmptyChromosome(int nGenes) {
		return new ChromosomeEggholder(new double[nGenes], new double[NOBJECTIVES]);
	}
}
