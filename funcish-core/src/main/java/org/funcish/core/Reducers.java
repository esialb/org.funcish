package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.ParaReducator;
import org.funcish.core.fn.Reducator;
import org.funcish.core.fn.Reducer;
import org.funcish.core.impl.AbstractReducer;
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

	public static <E, F extends E, M> Reducer<F, M> narrow(Class<F> f, final Reducer<E, M> reducer) {
		return new AbstractReducer<F, M>(f, reducer.m(), reducer.memoStart()) {
			@Override
			public M reduce0(M memo, F obj, Integer index) throws Exception {
				return reducer.reduce(memo, obj, index);
			}
		};
	}
	
	public static <E, N, M extends N> Reducer<E, N> widen(Class<N> n, final Reducer<E, M> reducer) {
		return new AbstractReducer<E, N>(reducer.e(), n, reducer.memoStart()) {
			@Override
			public N reduce0(N memo, E obj, Integer index) throws Exception {
				return reducer.reduce(reducer.m().cast(memo), obj, index);
			}
		};
	}
	
	private Reducers() {}
}
