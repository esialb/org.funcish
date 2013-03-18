package org.funcish.core;

import java.util.Arrays;

import org.funcish.core.ann.MethodFunction;
import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducator;
import org.junit.Assert;
import org.junit.Test;

public class FunctionsTest {
	@Test
	public void testFn_Object() throws Exception {
		Object plus = new Object() {
			@MethodFunction
			public int add(int lhs, int rhs) {
				return lhs + rhs;
			}
		};
		Function<?> f = Functions.fn(plus);
		Function<Integer> fi = Functions.fn(int.class, plus);
		Assert.assertNotNull(f);
		System.out.println(f);
	}
}
