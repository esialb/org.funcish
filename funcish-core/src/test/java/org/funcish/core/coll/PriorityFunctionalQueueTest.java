package org.funcish.core.coll;

public class PriorityFunctionalQueueTest 
extends AbstractFunctionalCollectionTest<PriorityFunctionalQueue<Integer>> {

	@Override
	protected PriorityFunctionalQueue<Integer> create(Integer... ints) {
		return new PriorityFunctionalQueue<Integer>(Integer.class, ints);
	}

}
