package principal;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import chromosomesPool.ChromosomeAckley;

public class Population {
	
	/*Population instances*/
	private Chromosome chromoPool[] = null;
	private int nChromosome = 0;
	private boolean busy;
	
	/*Statistical measures*/
	private double fitnessMean = 0;
	private double fitnessStd = 0;
	private double objectivesMean[] = null;
	private double objectivesStd[] = null;
	
	public static Population createPopulation(int nChromosome, int function) {
		Population p = new Population(nChromosome);
		for (int i = 0; i < nChromosome; i++)
			p.setChromosome(i, getInstanceOfChromosome(function));
		
		return p;
	}
	
	private static Chromosome getInstanceOfChromosome(int function) {
		switch(function) {
		case Configuration.ACKLEY:
			return ChromosomeAckley.createChromosome(Configuration.nGenes);
		default:
			System.out.println("Function not implemented yet.");
			return null;			
		}		
	}

	
	private Population(int nChromosome) {
		 this.chromoPool = new ChromosomeAckley[nChromosome];
		 this.nChromosome = nChromosome;
	}
	
	public Chromosome getChromosome(int index) {
		return this.chromoPool[index];
	}
	
	public void setChromosome(int index, Chromosome c) {
		this.chromoPool[index] = c;
	}
	
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	public boolean isBusy() {
		return this.busy;
	}
	
	public int getSize() {
		return this.nChromosome;
	}
	
	public double getFitnessMean() {
		return this.fitnessMean;
	}
	
	public double getFitnessStd() {
		return this.fitnessStd;
	}
	
	public double[] getObjectivesMean() {
		return this.objectivesMean;
	}
	
	public double[] getObjectivesStd() {
		return this.objectivesStd;
	}
		
	public void calculateFitnessMean() {
		double mean = 0;
		for (int i = 0; i < this.getSize(); i++) 
			mean += chromoPool[i].getFitness();
		this.fitnessMean = mean / this.getSize(); 
	}
	
	public void calculateFitnessStd() {
		double std = 0;
		for (int i = 0; i < this.getSize(); i++) 
			std += Math.pow(chromoPool[i].getFitness() - this.fitnessMean, 2);
		this.fitnessStd = std / (this.getSize() - 1);
	}
	
	public void calculateObjectivesMean() {
		int nObjectives = chromoPool[0].getObjectives().length;
		double mean = 0;
		
		for (int i = 0; i < nObjectives; i++) {
			for (int j = 0; j < this.getSize(); j++) 
				mean += chromoPool[j].getObjective(i);			
			this.objectivesMean[i] = mean / this.getSize();
			mean = 0;			
		}
	}
	
	public void calculateObjectivesStd() {
		int nObjectives = chromoPool[0].getObjectives().length;
		double std = 0;
		for (int i = 0; i < nObjectives; i++) {
			for (int j = 0; j < this.getSize(); j++)
				std += Math.pow(chromoPool[j].getObjective(i) - objectivesMean[i], 2);
			this.objectivesStd[i] = std / (this.getSize() - 1);
			std = 0;
		}
	}
	
	//TODO verificar
	public int getFittest() {
		int idx = 0; double bestFitness = Double.MAX_VALUE;
		for (int i = 0; i < this.getSize(); i++) {
			if(bestFitness > this.chromoPool[i].getFitness()) {
				bestFitness = this.chromoPool[i].getFitness();
				idx = i;
			}
		}
		return idx;			
	}
}
