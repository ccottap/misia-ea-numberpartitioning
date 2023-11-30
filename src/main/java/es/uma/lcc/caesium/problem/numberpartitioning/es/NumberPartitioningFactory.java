package es.uma.lcc.caesium.problem.numberpartitioning.es;

import java.util.List;

import es.uma.lcc.caesium.ea.operator.variation.VariationFactory;
import es.uma.lcc.caesium.ea.operator.variation.VariationOperator;

/**
 * A user-defined factory for variation operators for Number Partitioning
 * @author ccottap
 * @version 1.0
 */
public class NumberPartitioningFactory extends VariationFactory {

	@Override
	public VariationOperator create (String name, List<String> pars) {
		VariationOperator op = null;
				
		switch (name.toUpperCase()) {
		case "NPLS-FIRST":
			op = new NPLSFirstAscent(pars);
			break;		
		default:
			op = super.create(name, pars);
		}
		
		return op;
	}

}
