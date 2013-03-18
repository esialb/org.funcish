package org.funcish.clj;

import org.funcish.core.fn.Function;

import clojure.lang.IFn;
import clojure.lang.RestFn;

public class ClojureSupport {
	
	public static <T> IFn toClojure(Function<T> fn) {
		return new ProxyFunctionIFn<T>(fn);
	}
	
	public static Function<Object> fromClojure(RestFn fn) {
		return new RestFnProxyFunction(fn);
	}
	
	private ClojureSupport() {}
}
