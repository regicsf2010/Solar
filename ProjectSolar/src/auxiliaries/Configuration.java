package auxiliaries;

public class Configuration {
	public static int nPopulation = 10;
	public static int nChromosome = 5;
	public static int nGeneration = 100;
	public static int nGenes = 2;
	
	public static double crossoverRate = 0.8;
	public static double mutationRate = 0.01;
	public static double migrationRate = 0.1;
	public static final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	/*Simple enumerate structure*/
	public static final int ACKLEY = 0;
	public static final int SCHAFFERN2 = 1;
	public static final int SCHAFFERN4 = 2;
	public static final int SCHAFFERF7 = 3;
	public static final int EGGHOLDER = 4;
	public static final int GRIEWANK = 5;
	public static final int RASTRIGIN = 6;
	public static final int ROSENBROCK = 7;
	
}
