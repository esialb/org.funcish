package org.funcish.clj;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mappicator;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.ParaMappicator;
import org.funcish.core.fn.ParaPredicator;
import org.funcish.core.fn.ParaReducator;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reducator;
import org.funcish.core.fn.Reducer;

import clojure.lang.IFn;
import clojure.lang.IFn;

public class FromClojure {
	
	public static Function<Object> function(IFn fn) {
		return function(fn, null);
	}
	
	public static Function<Object> function(IFn fn, Long arity) {
		return new IFnProxyFunction(fn, arity == null ? null : arity.intValue());
	}
	
	public static Function<Boolean> bfunction(IFn fn) {
		return bfunction(fn, null);
	}
	
	public static Function<Boolean> bfunction(IFn fn, Long arity) {
		return new IFnProxyBooleanFunction(fn, arity == null ? null : arity.intValue());
	}

	public static Function<Boolean> bfunction(IFn fn, long arity) {
		return new IFnProxyBooleanFunction(fn, (int) arity);
	}

	public static Mapping<Object, Object> mapping(IFn fn) {
		return Mappings.mapping(Object.class, function(fn, 1L));
	}
	
	public static Mappicator<Object, Object> mappicator(IFn fn) {
		return Mappings.mappicator(mapping(fn));
	}
	
	public static ParaMappicator<Object, Object> paraMappicator(IFn fn) {
		return Mappings.paraMappicator(mapping(fn));
	}
	
	public static Reducer<Object, Object> reducer(IFn fn) {
		return Reducers.reducer(Object.class, function(fn, 2L));
	}
	
	public static Reducer<Object, Object> reducer(IFn fn, Object memoStart) {
		return Reducers.reducer(Object.class, memoStart, function(fn, 2L));
	}
	
	public static Reducator<Object, Object> reducator(IFn fn) {
		return Reducers.reducator(reducer(fn));
	}

	public static Reducator<Object, Object> reducator(IFn fn, Object memoStart) {
		return Reducers.reducator(reducer(fn, memoStart));
	}
	
	public static ParaReducator<Object, Object> paraReducator(IFn fn, IFn collator) {
		return Reducers.paraReducator(reducer(fn), reducer(collator));
	}

	public static Predicate<Object> predicate(IFn fn) {
		return Predicates.predicate(Object.class, bfunction(fn, 1));
	}
	
	public static Predicate<Object> predicator(IFn fn) {
		return Predicates.predicator(predicate(fn));
	}
	
	public static ParaPredicator<Object> paraPredicator(IFn fn) {
		return Predicates.paraPredicator(predicate(fn));
	}
	
	private FromClojure() {}
}
