package auxiliaries;

public class Configuration {
	public static int nPopulation = 100;
	public static int nChromosome = 50;
	public static int nGeneration = 100;
	public static int nGenes = 2;
	
	public static double crossoverRate = 0.8;
	public static double mutationRate = 0.01;
	public static double migrationRate = 0.1;
	public static final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	
}
