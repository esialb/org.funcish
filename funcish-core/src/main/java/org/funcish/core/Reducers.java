package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.ParaReducator;
import org.funcish.core.fn.Reducator;
import org.funcish.core.fn.Reducer;
import org.funcish.core.impl.AbstractReducator;
import org.funcish.core.impl.ProxyParaReducator;
import org.funcish.core.impl.ProxyReducator;
import org.funcish.core.impl.ProxyReducer;

/**
 * Utility methods for creating or wrapping {@link Reducer}s
 * @author robin
 *
 */
public class Reducers {
	/**
	 * Return a {@link Reducer} from the argument {@link Function}, with a {@code null}
	 * intial memo
	 * @param e
	 * @param target
	 * @return
	 */
	public static <E, M> Reducer<E, M> reducer(Class<E> e, Function<M> target) {
		return reducer(e, null, target);
	}
	
	/**
	 * Return a {@link Reducer} from the argument {@link Function} with the specified
	 * intial memo
	 * @param e
	 * @param memoStart
	 * @param target
	 * @return
	 */
	public static <E, M> Reducer<E, M> reducer(Class<E> e, M memoStart, Function<M> target) {
		return new ProxyReducer<E, M>(e, memoStart, target);
	}
	
	/**
	 * Return a {@link Reducator} from the argument {@link Reducer}, either by casting,
	 * or failing that, wrapping
	 * @param target
	 * @return
	 */
	public static <E, M> Reducator<E, M> reducator(Reducer<E, M> target) {
		if(target instanceof Reducator<?, ?>)
			return (Reducator<E, M>) target;
		return new ProxyReducator<E, M>(target);
	}
	
	/**
	 * Return a {@link ParaReducator} from the argument {@link Reducer}, either by casting,
	 * or failing that, wrapping
	 * @param target
	 * @param collator
	 * @return
	 */
	public static <E, M> ParaReducator<E, M> paraReducator(Reducer<E, M> target, Reducer<M, M> collator) {
		return new ProxyParaReducator<E, M>(target, collator);
	}

	/**
	 * Return a new {@link Reducer} that narrows the inputs of the argument
	 * @param f
	 * @param reducer
	 * @return
	 */
	public static <E, F extends E, M> Reducator<F, M> narrow(Class<F> f, final Reducer<E, M> reducer) {
		return new NarrowingReducator<E, F, M>(f, reducer.m(), reducer.memoStart(), reducer);
	}
	
	private static class NarrowingReducator<E, F extends E, M> extends AbstractReducator<F, M> {
		private final Reducer<E, M> reducer;
	
		private NarrowingReducator(Class<F> e, Class<M> m, M memoStart,
				Reducer<E, M> reducer) {
			super(e, m, memoStart);
			this.reducer = reducer;
		}
	
		@Override
		public M reduce0(M memo, F obj, Integer index) throws Exception {
			return reducer.reduce(memo, obj, index);
		}
	}

	private Reducers() {}
}
