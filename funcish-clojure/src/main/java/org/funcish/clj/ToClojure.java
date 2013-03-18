package org.funcish.clj;

import org.funcish.core.fn.Function;

import clojure.lang.IFn;

public class ToClojure {
	public static <T> IFn clojureIFn(Function<T> fn) {
		return new ProxyFunctionIFn<T>(fn);
	}
	
	private ToClojure() {}
}
