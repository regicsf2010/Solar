package interfaces;

import principal.Population;

public interface Crossover {
	
	/**
	 * 
	 * @param parents 
	 * @return
	 */
	public Population doCrossover(Population parents);
}