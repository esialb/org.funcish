package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.ParaReducator;
import org.funcish.core.fn.Reducator;
import org.funcish.core.fn.Reducer;
import org.funcish.core.impl.ProxyParaReducator;
import org.funcish.core.impl.ProxyReducator;
import org.funcish.core.impl.ProxyReducer;

public class Reducers {
	
	public static <E, M> Reducer<E, M> reducer(Class<E> e, Function<M> target) {
		return reducer(e, null, target);
	}
	
	public static <E, M> Reducer<E, M> reducer(Class<E> e, M memoStart, Function<M> target) {
		return new ProxyReducer<E, M>(e, memoStart, target);
	}
	
	public static <E, M> Reducator<E, M> reducator(Reducer<E, M> target) {
		return new ProxyReducator<E, M>(target);
	}
	
	public static <E, M> ParaReducator<E, M> paraReducator(Reducer<E, M> target, Reducer<M, M> collator) {
		return new ProxyParaReducator<E, M>(target, collator);
	}

	private Reducers() {}
}