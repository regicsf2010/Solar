package principal;

import abstracts.Chromosome;
import auxiliaries.Configuration.Ackley;
import auxiliaries.Configuration.Eggholder;
import auxiliaries.Configuration.Griewank;
import auxiliaries.Configuration.Rastrigin;
import auxiliaries.Configuration.Rosenbrock;
import auxiliaries.Configuration.SchafferF7;
import auxiliaries.Configuration.SchafferN2;
import auxiliaries.Configuration.SchafferN4;
import chromosomesPool.ChromosomeAckley;
import chromosomesPool.ChromosomeEggholder;
import chromosomesPool.ChromosomeGriewank;
import chromosomesPool.ChromosomeRastrigin;
import chromosomesPool.ChromosomeRosenbrock;
import chromosomesPool.ChromosomeSchafferF7;
import chromosomesPool.ChromosomeSchafferN2;
import chromosomesPool.ChromosomeSchafferN4;

public class Population {
	
	/*Population instances*/
	private Chromosome chromoPool[] = null;
	private int nChromosome = 0;
		
	/*Statistical measures*/
	private double fitnessMean = 0;
	private double fitnessStd = 0;
	private double objectivesMean[] = null;
	private double objectivesStd[] = null;
	
	public static Population createPopulation(int nChromosome, int function) {
		Population p = new Population(nChromosome);
		for (int i = 0; i < nChromosome; i++)
			p.setChromosome(i, chromosomeFactory(function));
		
		return p;
	}
	
	private static Chromosome chromosomeFactory(int function) {
		switch(function) {
		case Ackley.ID:
			return ChromosomeAckley.createChromosome(Ackley.NGENES);
		case SchafferN2.ID:
			return ChromosomeSchafferN2.createChromosome(SchafferN2.NGENES);
		case SchafferN4.ID:
			return ChromosomeSchafferN4.createChromosome(SchafferN4.NGENES);
		case SchafferF7.ID:
			return ChromosomeSchafferF7.createChromosome(SchafferF7.NGENES);
		case Eggholder.ID:
			return ChromosomeEggholder.createChromosome(Eggholder.NGENES);
		case Griewank.ID:
			return ChromosomeGriewank.createChromosome(Griewank.NGENES);
		case Rastrigin.ID:
			return ChromosomeRastrigin.createChromosome(Rastrigin.NGENES);
		case Rosenbrock.ID:
			return ChromosomeRosenbrock.createChromosome(Rosenbrock.NGENES);
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
