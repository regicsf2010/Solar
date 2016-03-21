package principal;

import abstracts.Chromosome;
import auxiliaries.Configuration;
import chromosomesPool.ChromosomeAckley;

public class Population {
	private Chromosome population[] = null;
	private int nChromosome = 0;
	
	private double fitnessMean = 0;
	private double fitnessStd = 0;
	private double objectivesMean[] = null;
	private double objectivesStd[] = null;	
		
	public Population(int nChromosome) {
		 this.population = new ChromosomeAckley[nChromosome];
		 this.nChromosome = nChromosome;
	}
	
	public Chromosome getChromosome(int index) {
		return this.population[index];
	}
	
	public void setChromosome(int index, Chromosome c) {
		this.population[index] = c;
	}
	
	public static Population createPopulation(int nChromosome) {
		Population p = new Population(nChromosome);
		for (int i = 0; i < nChromosome; i++)
			p.setChromosome(i, ChromosomeAckley.createChromosome(Configuration.nGenes));
		
		return p;
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
			mean += population[i].getFitness();
		this.fitnessMean = mean / this.getSize(); 
	}
	
	public void calculateFitnessStd() {
		double std = 0;
		for (int i = 0; i < this.getSize(); i++) 
			std += Math.pow(population[i].getFitness() - this.fitnessMean, 2);
		this.fitnessStd = std / (this.getSize() - 1);
	}
	
	public void calculateObjectivesMean() {
		int nObjectives = population[0].getObjectives().length;
		double mean = 0;
		
		for (int i = 0; i < nObjectives; i++) {
			for (int j = 0; j < this.getSize(); j++) 
				mean += population[j].getObjective(i);			
			this.objectivesMean[i] = mean / this.getSize();
			mean = 0;			
		}
	}
	
	public void calculateObjectivesStd() {
		int nObjectives = population[0].getObjectives().length;
		double std = 0;
		for (int i = 0; i < nObjectives; i++) {
			for (int j = 0; j < this.getSize(); j++)
				std += Math.pow(population[j].getObjective(i) - objectivesMean[i], 2);
			this.objectivesStd[i] = std / (this.getSize() - 1);
			std = 0;
		}
	}
	
	//TODO verificar	
	public Chromosome getFittest() {
		int idx = 0; double bestFitness = Double.MAX_VALUE;
		for (int i = 0; i < this.getSize(); i++) {
			if(bestFitness > this.population[i].getFitness()) {
				bestFitness = this.population[i].getFitness();
				idx = i;
			}
		}
		return this.population[idx];			
	}
}
