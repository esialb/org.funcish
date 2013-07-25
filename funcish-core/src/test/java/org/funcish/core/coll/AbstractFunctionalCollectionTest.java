package org.funcish.core.coll;

import java.util.Collection;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.MultiMapping;
import org.funcish.core.fn.MultiReceiver;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reduction;
import org.funcish.core.impl.AbstractMapping;
import org.funcish.core.impl.AbstractMultiMapping;
import org.funcish.core.impl.AbstractPredicate;
import org.funcish.core.impl.AbstractReduction;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractFunctionalCollectionTest<F extends FunctionalCollection<Integer>> {
	protected abstract F create(Integer... ints);
	
	@Test
	public void testAdd() {
		F ints = create();
		ints.add(1);
		ints.add(2);
		try {
			((Collection) ints).add("3");
			Assert.fail();
		} catch(ClassCastException expected) {
		}
		Assert.assertEquals(create(1,2), ints);
	}

	@Test
	public void testMap() {
		Mapping<Integer, Integer> timesTwo = new AbstractMapping<Integer, Integer>(Integer.class, Integer.class) {
			@Override
			public Integer map0(Integer key, Integer index) throws Exception {
				return 2 * key;
			}
		};
		F ints = create(1,2,3);
		Assert.assertEquals(create(2,4,6), ints.map(timesTwo));
	}
	
	@Test
	public void testMultiMap() {
		MultiMapping<Integer, Integer> duplicate = new AbstractMultiMapping<Integer, Integer>(Integer.class, Integer.class) {
			@Override
			public void map0(Integer key, MultiReceiver<? super Integer> receiver, Integer index)
					throws Exception {
				receiver.receive(key);
				receiver.receive(key);
			}
		};
		F ints = create(1,2,3);
		Assert.assertEquals(create(1,1,2,2,3,3), ints.map(duplicate));
	}
	
	@Test
	public void testFilter() {
		Predicate<Integer> even = new AbstractPredicate<Integer>(Integer.class) {
			@Override
			public boolean test0(Integer value, Integer index) throws Exception {
				return value % 2 == 0;
			}
		};
		F ints = create(1,2,3);
		Assert.assertEquals(create(2), ints.filter(even));
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
		F ints = create(1,2,3);
		Assert.assertEquals((Object) 6, ints.reduce(sum));
	}
}
