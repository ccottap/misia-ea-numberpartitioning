package es.uma.lcc.caesium.problem.numberpartitioning.ea;

import java.util.List;
import java.util.Set;

import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.operator.variation.mutation.MutationOperator;
import es.uma.lcc.caesium.problem.numberpartitioning.NumberPartitioning;

/**
 * A local search method for the Number Partitioning Problem: Hill Climbing with first ascent
 * @author ccottap
 * @version 1.0
 *
 */
public class NPLSFirstAscent extends MutationOperator {
	/**
	 * Creates the operator. 
	 * @param pars String representation of the mutation probability
	 */
	public NPLSFirstAscent(List<String> pars) {
		super(pars);
	}

	@Override
	protected Individual _apply(List<Individual> parents) {
		NumberPartitioningObjectiveFunction bpof = (NumberPartitioningObjectiveFunction)obj;
		NumberPartitioning np = bpof.getNumberPartitioningData();
		Set<Integer> partition = bpof.genotype2Collection(parents.get(0).getGenome());
		int tests = 0;
		
		// TODO completar este método
		// Debe realizarse un algoritmo de ascensión de colinas por primer ascenso:
		// calcular el balance actual de la solución
		// repetir
		//		para cada número de la instancia en un orden aleartorio 
		//			incrementar la variable tests
		//			calcular cuál sería el balance si se invirtiera el estado del número (si está en la partición, sacarlo; si no está, meterlo).
		//			si el balance modificado es mejor que el balance anterior, marcar mejora y salir del buvle
		//		finpara
		// miestras haya habido mejora repetir

		
		obj.addExtraCost((double)tests/(double)np.getNum());
		
		Individual ind = new Individual();
		ind.setGenome(bpof.collection2Genotype(partition));
		ind.touch();
		return ind;
	}

	@Override
	public String toString() {
		return "NPLS-FirstAscent(" + prob + ")";
	}

}
