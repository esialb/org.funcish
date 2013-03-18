package org.funcish.clj;

import org.funcish.core.Reducers;
import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducator;
import org.funcish.core.fn.Reducer;

import clojure.lang.IFn;
import clojure.lang.RestFn;

public class FromClojure {
	
	public static Function<Object> function(RestFn fn) {
		return new RestFnProxyFunction(fn);
	}
	
	public static Reducer<Object, Object> reducer(RestFn fn) {
		return Reducers.reducer(Object.class, function(fn));
	}
	
	public static Reducer<Object, Object> reducer(RestFn fn, Object memoStart) {
		return Reducers.reducer(Object.class, memoStart, function(fn));
	}
	
	public static Reducator<Object, Object> reducator(RestFn fn) {
		return Reducers.reducator(reducer(fn));
	}

	public static Reducator<Object, Object> reducator(RestFn fn, Object memoStart) {
		return Reducers.reducator(reducer(fn, memoStart));
	}

	private FromClojure() {}
}
