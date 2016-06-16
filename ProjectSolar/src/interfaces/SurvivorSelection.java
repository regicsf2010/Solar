package interfaces;

import principal.Population;

public interface SurvivorSelection {
	
	public Population doSurvivorSelection(Population p, Population offspring);
}
