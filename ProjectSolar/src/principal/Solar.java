package principal;

import auxiliaries.Configuration;
import interfaces.Crossover;
import interfaces.Mutation;
import interfaces.ParentSelection;
import interfaces.SurvivorSelection;

/**
 * This class implements a Multipopulation Parallel Genetic Algorithm (MPGA).
 * Multipopulations are demes or islands.
 * @author reginaldo
 * @date   February/01/2016 
 *
 */
public class Solar implements Runnable {
	
	private Population p = null;
	private Population selected = null;
	private int function = -1;
	private int id = -1; 
	
	private ParentSelection parentSelecionI = null;
	private Crossover crossoverI = null;
	private Mutation mutationI = null;
	private SurvivorSelection survivorSelectionI = null;
	
	public Solar(int function, int id) {
		this.function = function;
		this.id = id;
	}
	
	public Population getPopulation() {
		return this.p;
	}
	
	private void initializePopulation() {
		this.p = Population.createPopulation(Configuration.NCHROMOSOME, this.function, true);
	}
	
	private void calculateFitness(Population pop) {
		for (int i = 0; i < pop.getSize(); i++)
			pop.getChromosome(i).evaluate();		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setParentSelectionInterface(ParentSelection parentSelecionI) {
		this.parentSelecionI = parentSelecionI;
	}
	
	public void setCrossoverInterface(Crossover crossoverI) {
		this.crossoverI = crossoverI;
	}
	
	public void setMutationInterface(Mutation mutationI) {
		this.mutationI = mutationI;
	}
	
	public void setSurvivorSelectionInterface(SurvivorSelection survivorSelectionI) {
		this.survivorSelectionI = survivorSelectionI;
	}
	
	@Override
	public void run() {
		
		this.initializePopulation();
		this.calculateFitness(p);
		
		for (int i = 0; i < Configuration.NGENERATION; i++) {
			this.selected = this.parentSelecionI.doParentSelection(p);
			this.selected = this.crossoverI.doCrossover(selected);
			this.mutationI.doMutation(selected);
			this.calculateFitness(selected);
			this.p = this.survivorSelectionI.doSurvivorSelection(p, selected);
		}
		
	}
}
