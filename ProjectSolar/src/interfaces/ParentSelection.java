package interfaces;

import principal.Population;

public interface ParentSelection {
	
	/**
	 * This scheme returns a copy of each selected parent in the population. 
	 * @return The mating pool, i.e., the parents.
	 */
	public Population doParentSelection(Population p);
}
