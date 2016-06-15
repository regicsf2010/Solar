package auxiliaries;

public class Configuration {
	public static int nPopulation = 10;
	public static int nChromosome = 5;
	public static int nGeneration = 100;
	
	
	public static double crossoverRate = 0.8;
	public static double mutationRate = 0.01;
	public static double migrationRate = 0.1;
	public static final MersenneTwisterFast mt = new MersenneTwisterFast(System.currentTimeMillis());
	
	/*Simple structures for each problem*/
	public class Ackley { public static final int ID = 0; public static final int NGENES = 2; }
	public class SchafferN2 { public static final int ID = 1; public static final int NGENES = 2; }
	public class SchafferN4 { public static final int ID = 2; public static final int NGENES = 2; }
	public class SchafferF7 { public static final int ID = 3; public static final int NGENES = 2; }
	public class Eggholder { public static final int ID = 4; public static final int NGENES = 2; }
	public class Griewank { public static final int ID = 5; public static final int NGENES = 1; }
	public class Rastrigin { public static final int ID = 6; public static final int NGENES = 1; }
	public class Rosenbrock { public static final int ID = 7; public static final int NGENES = 2; }
}
