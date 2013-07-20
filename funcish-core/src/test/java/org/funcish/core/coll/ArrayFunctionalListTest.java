package org.funcish.core.coll;

public class ArrayFunctionalListTest 
extends AbstractFunctionalCollectionTest<FunctionalList<Integer>> {

	@Override
	protected FunctionalList<Integer> create(Integer... ints) {
		return new ArrayFunctionalList<Integer>(Integer.class, ints);
	}

}
