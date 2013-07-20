package org.funcish.core.coll;

public class ArrayFunctionalDequeTest 
extends AbstractFunctionalCollectionTest<FunctionalDeque<Integer>> {

	@Override
	protected FunctionalDeque<Integer> create(Integer... ints) {
		return new ArrayFunctionalDeque<Integer>(Integer.class, ints);
	}

}
