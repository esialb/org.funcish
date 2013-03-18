package org.funcish.clj;

import org.funcish.core.Functions;
import org.funcish.core.ann.MethodFunction;
import org.funcish.core.fn.Function;

public class ToClojureTestSupport {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Function<Integer> PLUS = (Function) Functions.fn(new Object() {
		@MethodFunction
		public int plus(int lhs, int rhs) {
			return lhs + rhs;
		}
	});
}
