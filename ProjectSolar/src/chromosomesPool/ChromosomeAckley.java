package chromosomesPool;
import abstracts.Chromosome;
import problems.MathFunctions;

public class ChromosomeAckley extends Chromosome {
	private static final double INFIMUM = -40;
	private static final double MAXIMUM = 40;
	private static final int NOBJECTIVES = 1;

	private ChromosomeAckley(double genes[], double objectives[]) {
		super(genes, objectives);
	}
	
	public static ChromosomeAckley createChromosome(int nGenes) {
		double genes[] = initializeGenesAtRandom(nGenes, INFIMUM, MAXIMUM); // From superclass
		return new ChromosomeAckley(genes, new double[NOBJECTIVES]);
	}
	
	@Override
	public void evaluate() {
		double value = MathFunctions.Ackley(super.getGenes());
		super.setObjective(0, value); // zero is necessary. 
		super.setFitness(value);	  // Ackley's function has one objective (fitness).
	}

	public static ChromosomeAckley createEmptyChromosome(int nGenes) {
		return new ChromosomeAckley(new double[nGenes], new double[NOBJECTIVES]);
	}
}
