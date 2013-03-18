package org.funcish.clj;

import org.funcish.core.Functions;
import org.funcish.core.Reducers;
import org.funcish.core.ann.MethodFunction;
import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducer;

public class ToClojureTestSupport {
	@SuppressWarnings("unchecked")
	public static Function<Integer> PLUS = (Function) Functions.fn(new Object() {
		@MethodFunction
		public int plus(int lhs, int rhs) {
			return lhs + rhs;
		}
	});
}
