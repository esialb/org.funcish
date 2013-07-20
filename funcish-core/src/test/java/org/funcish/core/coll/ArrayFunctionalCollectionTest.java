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

public class ArrayFunctionalCollectionTest {
	@Test
	public void testAdd() {
		FunctionalCollection<Integer> ints = new ArrayFunctionalCollection<Integer>(Integer.class);
		ints.add(1);
		ints.add(2);
		try {
			((FunctionalCollection) ints).add("3");
			Assert.fail();
		} catch(ClassCastException expected) {
		}
		Assert.assertEquals(new ArrayCollection<Integer>(1,2), ints);
	}
	
	@Test
	public void testMap() {
		Mapping<Integer, Integer> timesTwo = new AbstractMapping<Integer, Integer>(Integer.class, Integer.class) {
			@Override
			public Integer map0(Integer key, Integer index) throws Exception {
				return 2 * key;
			}
		};
		FunctionalCollection<Integer> ints = new ArrayFunctionalCollection<Integer>(Integer.class, 1,2,3);
		Assert.assertEquals(new ArrayFunctionalCollection<Integer>(Integer.class, 2,4,6), ints.map(timesTwo));
	}
	
	@Test
	public void testFilter() {
		Predicate<Integer> even = new AbstractPredicate<Integer>(Integer.class) {
			@Override
			public boolean test0(Integer value, Integer index) throws Exception {
				return value % 2 == 0;
			}
		};
		FunctionalCollection<Integer> ints = new ArrayFunctionalCollection<Integer>(Integer.class, 1,2,3);
		Assert.assertEquals(new ArrayFunctionalCollection<Integer>(Integer.class, 2), ints.filter(even));
	}
	
	@Test
	public void testReduce() {
		Reduction<Integer, Integer> sum = new AbstractReduction<Integer, Integer>(Integer.class, Integer.class, 0) {
			@Override
			public Integer reduce0(Integer memo, Integer obj, Integer index)
					throws Exception {
				return memo + obj;
			}
		};
		FunctionalCollection<Integer> ints = new ArrayFunctionalCollection<Integer>(Integer.class, 1,2,3);
		Assert.assertEquals((Object) 6, ints.reduce(sum));
	}
}
