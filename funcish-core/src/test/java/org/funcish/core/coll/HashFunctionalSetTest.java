package org.funcish.core.coll;

public class HashFunctionalSetTest 
extends AbstractFunctionalCollectionTest<FunctionalSet<Integer>> {

	@Override
	protected FunctionalSet<Integer> create(Integer... ints) {
		return new HashFunctionalSet<Integer>(Integer.class, ints);
	}

}
