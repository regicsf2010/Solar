package interfaces;

import principal.Population;

public interface Migration extends Runnable {
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 */
	public void doMigration(Population p1, Population p2);
	
}
