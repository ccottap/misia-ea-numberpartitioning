package es.uma.lcc.caesium.problem.numberpartitioning.ea;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import es.uma.lcc.caesium.ea.base.Genotype;
import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.DiscreteObjectiveFunction;
import es.uma.lcc.caesium.ea.fitness.OptimizationSense;
import es.uma.lcc.caesium.problem.numberpartitioning.NumberPartitioning;


/**
 * Objective function for the Number Partitioning Problem. Solutions are represented as 
 * a binary string of the same length as number of values, each position indicating in 
 * which partition the value is.
 * @author ccottap
 * @version 1.0
 */
public class NumberPartitioningObjectiveFunction extends DiscreteObjectiveFunction {
	/**
	 * the problem instance
	 */
	private NumberPartitioning np;
	
	/**
	 * Basic constructor of the objective function
	 * @param bp the problem instance
	 */
	public NumberPartitioningObjectiveFunction(NumberPartitioning bp) {
		super(bp.getNum(), 2);
		this.np = bp;
	}

	@Override
	public OptimizationSense getOptimizationSense() {
		return OptimizationSense.MINIMIZATION;
	}
	
	/**
	 * Returns the Number Partitioning instance being solved
	 * @return the Number Partitioning instance being solved
	 */
	public NumberPartitioning getNumberPartitioningData () {
		return np;
	}
	
	/**
	 * Creates a partition of the values (those in partition 1)
	 * @param g the genotype
	 * @return a set with the indices of the values
	 */
	public Set<Integer> genotype2Collection(Genotype g) {
		int l = np.getNum();
		Set<Integer> partition = new HashSet<Integer>(l);
		for (int k=0; k<l; k++) {
			if (((int)g.getGene(k)) == 1)
				partition.add(k);
		}
		return partition;
	}
	
	/**
	 * Creates a genotype given a collection of indices
	 * @param partition a collection of indices
	 * @return a genotype
	 */
	public Genotype collection2Genotype(Collection<Integer> partition) {
		int l = np.getNum();
		Genotype g = new Genotype(l);
		for (int k=0; k<l; k++) {
			if (partition.contains(k)) 
				g.setGene(k, 1);
			else
				g.setGene(k, 0);
		}
		return g;
	}

	@Override
	protected double _evaluate(Individual i) {
		return Math.abs(np.balance(genotype2Collection(i.getGenome())));
	}



}
