package abstracts;

import java.text.DecimalFormat;

import auxiliaries.Configuration;

public abstract class Chromosome {
	private double fitness;
	private double genes[];
	private double objectives[];
	
	/*This variable controls the access of the chromosome*/
	private boolean busy;
	
	public Chromosome(double genes[], double objectives[]) {
		this.genes = genes;
		this.objectives = objectives;
		this.setFitness(0);
	}
	
	protected void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double getFitness() {
		return this.fitness;
	}
	
	public void setGene(int index, double value) {
		this.genes[index] = value;
	}
	
	public double getGene(int index) {
		return this.genes[index];
	}
	
	/**
	 * Return all genes.
	 * @return Array of genes
	 */
	public double[] getGenes() {
		return this.genes;
	}
	
	public void setObjective(int index, double value) {
		this.objectives[index] = value;
	}
	
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	public boolean isBusy() {
		return this.busy;
	}
	
	

	public double getObjective(int index) {
		return this.objectives[index];
	}
	
	/**
	 * Return all objectives.
	 * @return Array of objectives
	 */
	public double[] getObjectives() {
		return this.objectives;
	}	
	
	/**
	 * For output purpose.
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.###");
		String output = new String("Genes = [ "); // output genes
		for (int i = 0; i < genes.length; i++) 
			output += df.format(this.genes[i]) + " ";
		output += "]\n";
		
		output += "Objectives = [ "; // output objectives
		for (int i = 0; i < objectives.length; i++)
			output += df.format(this.objectives[i]) + " ";
		output += "]\n";
		
		output += "Fitness = " + df.format(this.fitness) + "\n"; // output fitness
		
		output += "Warning = Rounded (format #.###)\n";
		return output;
	}

	/**
	 * This method must be protected and static fashion.
	 * Protected: subclass purpose only.
	 * Static: subclass purpose only. The subclass method that uses this method is static. 
	 * Therefore, this method must be static as well.
	 * @param nGenes
	 * @param infimum
	 * @param maximum
	 * @return
	 */
	protected static double[] initializeGenesAtRandom(int nGenes, double infimum, double maximum) {
		double genes[] = new double[nGenes]; double val = 0;
		for (int i = 0; i < genes.length; i++) {
			val = Configuration.mt.nextDouble(true, true); // inclusive [0, 1]
			genes[i] = (1 - val) * infimum + val * maximum;
		}
		return genes;
	}
	
	/**
	 * Abstract method for setting the fitness value.
	 * Subclasses must call setFitness() to properly store fitness.
	 */
	public abstract void evaluate();
		
}
