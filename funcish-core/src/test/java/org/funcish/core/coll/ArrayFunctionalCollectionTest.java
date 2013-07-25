package org.funcish.core.coll;

import java.util.Arrays;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reduction;
import org.funcish.core.impl.AbstractMapping;
import org.funcish.core.impl.AbstractPredicate;
import org.funcish.core.impl.AbstractReduction;
import org.junit.Assert;
import org.junit.Test;

public class ArrayFunctionalCollectionTest extends AbstractFunctionalCollectionTest<FunctionalCollection<Integer>>{

	@Override
	protected FunctionalCollection<Integer> create(Integer... ints) {
		return new ArrayFunctionalCollection<Integer>(Integer.class, ints);
	}

}
