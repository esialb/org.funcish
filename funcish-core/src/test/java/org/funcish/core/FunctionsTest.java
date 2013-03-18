package org.funcish.core;

import java.util.concurrent.Callable;

import org.funcish.core.ann.MethodFunction;
import org.funcish.core.fn.Function;
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
		Assert.assertNotNull(f);
		System.out.println(f);
	}
	
	@Test
	public void testFn_Callable() throws Exception {
		Callable<?> foo = new Callable<Object>() {
			@Override
			public String call() throws Exception {
				return "bar";
			}
		};
		Function<?> f = Functions.fn(foo);
		System.out.println(f);
	}
}
