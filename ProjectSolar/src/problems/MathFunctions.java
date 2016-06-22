package problems;

public class MathFunctions {

	/**
	 * Ackley's function. The minimum is f(0, 0) = 0.
	 * Recommendation: vars should be [-4, 4].
	 * @param vars
	 * @return
	 */
	public static double Ackley(double vars[]) {
		double s = 0;
		for (double d : vars) 
			s += Math.pow(d, 2);
		double p1 = -20 * Math.exp(-0.2 * Math.sqrt(1f / vars.length * s));
				
		s = 0;
		for (double d : vars) 
			s += Math.cos(2 * Math.PI * d);
		double p2 = -Math.exp(1f / vars.length * s) + 20 + Math.E;
		return p1 + p2;
	}
	
	/**
	 * Schaffer function N.2. The minimum is f(0, 0) = 0.
	 * Recommendation: vars should be [-100, 100]. 
	 * @param vars
	 * @return
	 */
	public static double SchafferN2(double vars[]) {
		double p1 = 0, p2 = 0;
		p1 = Math.pow(Math.sin(Math.sqrt(Math.pow(vars[0], 2) + Math.pow(vars[1], 2))), 2) - 0.5;
		p2 = Math.pow(1 + 0.001 * (Math.pow(vars[0], 2) + Math.pow(vars[1], 2)), 2);
		return 0.5 + p1 / p2;
	}

	/**
	 * Schaffer function N.4. The minimum is f(0, 1.25313) = 0.29257863204552975.
	 * Recommendation: vars should be [-100, 100]. 
	 * @param vars
	 * @return
	 */
	public static double SchafferN4(double vars[]) {
		double p1 = 0, p2 = 0;
		p1 = Math.pow(Math.cos(Math.sin(Math.abs(Math.pow(vars[0], 2) - Math.pow(vars[1], 2)))), 2) - 0.5;
		p2 = Math.pow(1 + 0.001 * (Math.pow(vars[0], 2) + Math.pow(vars[1], 2)), 2);
		return 0.5 + p1 / p2;
	}
	
	/**
	 * Schaffer F7. The minimum is f(0, 0) = 0.
	 * Recommendation: vars should be [-100, 100]. 
	 * @param vars
	 * @return
	 */
	public static double SchafferF7(double vars[]) {
		double normalizer = 1f / (vars.length - 1);
		double si = 0, sum = 0;
		for (int i = 0; i < vars.length - 1; i++) {
			si = Math.sqrt(Math.pow(vars[i], 2) + Math.pow(vars[i + 1], 2));
			sum += Math.pow(normalizer * Math.sqrt(si) * (Math.sin(50 * Math.pow(si, 0.2)) + 1), 2); 
		}		
		return sum;
	}
	
	/**
	 * Eggholder function. The minimum is f(512, 404.2319) = -959.6406627106155.
	 * Recommendation: vars should be [-512, 512].
	 * @param vars
	 * @return
	 */
	public static double Eggholder(double vars[]) {
		double p1 = 0, p2 = 0;
		p1 = -(vars[1] + 47) * Math.sin(Math.sqrt(Math.abs(vars[0] / 2 + (vars[1] + 47))));
		p2 = -vars[0] * Math.sin(Math.sqrt(Math.abs(vars[0] - (vars[1] + 47)))); 
		return p1 + p2;
	}
	
	/**
	 * Griewank function. The minimum is f(0) = 0.
	 * Recommendation: vars should be [-600, 600].
	 * @param vars
	 * @return
	 */
	public static double Griewank(double vars[]) {
		double p1 = 0, p2 = 1; // must be one
		for (double d : vars)
			p1 += Math.pow(d, 2);
		p1 = 1 + p1 / 4000;
		for (int i = 0; i < vars.length; i++) 
			p2 *= Math.cos(vars[i] / Math.sqrt(i+1));		
		return p1 - p2;
	}	
	
	/**
	 * Rastrigin function. The minimum is f(0) = 0.
	 * @param vars
	 * @return
	 */
	public static double Rastrigin(double vars[]) {
		double p = 0, a = 10;
		for (double d : vars) 
			p += Math.pow(d, 2) - a * Math.cos(2 * Math.PI * d);
		
		return a * vars.length + p;
	}
	
	/**
	 * Rosenbrock function. The minimum is f(1, 1) = 0.
	 * Recommendation: vars should be [-200, 200]. 
	 * @param vars
	 * @return
	 */
	public static double Rosenbrock(double vars[]) {
		double p = 0, a = 1, b = 100;
		if(vars.length > 1) {
			for (int i = 0; i < vars.length - 1; i++) 
				p += b * Math.pow((vars[i + 1] - Math.pow(vars[i], 2)), 2) + Math.pow(a - vars[i], 2);
		} else { // WARNING: please, do not use this 'else' version (it is a polynomial function only)
			p = b * Math.pow(vars[0], 4) + Math.pow(vars[0] - a, 2);
		}
		return p;
	}
	
}