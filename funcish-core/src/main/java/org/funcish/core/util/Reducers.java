/**
 * org.funcish: functional utilities for Java
 * 
 * Copyright 2013 Robin Kirkman.
 * 
 * Released under a BSD license.
 * 
 * Copyright (c) 2013, Robin Kirkman
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *  o  Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *  o  Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *  o  Neither the name of org.funcish nor the names of its contributors may be used to endorse 
 *     or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.funcish.core.util;

import java.util.Collection;
import java.util.Comparator;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.ParaReducer;
import org.funcish.core.fn.Reducer;
import org.funcish.core.fn.Reduction;
import org.funcish.core.impl.AbstractReducer;
import org.funcish.core.impl.ProxyParaReducer;
import org.funcish.core.impl.ProxyReducer;
import org.funcish.core.impl.ProxyReduction;

/**
 * Utility methods for creating or wrapping {@link Reduction}s
 * @author robin
 *
 */
public class Reducers {
	/**
	 * Return a {@link Reduction} from the argument {@link Function}, with a {@code null}
	 * intial memo
	 * @param e
	 * @param target
	 * @return
	 */
	public static <E, M> Reduction<E, M> reduction(Class<E> e, Function<M> target) {
		return reduction(e, null, target);
	}
	
	/**
	 * Return a {@link Reduction} from the argument {@link Function} with the specified
	 * intial memo
	 * @param e
	 * @param memoStart
	 * @param target
	 * @return
	 */
	public static <E, M> Reduction<E, M> reduction(Class<E> e, M memoStart, Function<M> target) {
		return new ProxyReduction<E, M>(e, memoStart, target);
	}
	
	/**
	 * Return a {@link Reducer} from the argument {@link Reduction}, either by casting,
	 * or failing that, wrapping
	 * @param target
	 * @return
	 */
	public static <E, M> Reducer<E, M> reducer(Reduction<E, M> target) {
		if(target instanceof Reducer<?, ?>)
			return (Reducer<E, M>) target;
		return new ProxyReducer<E, M>(target);
	}
	
	/**
	 * Return a {@link ParaReducer} from the argument {@link Reduction}, either by casting,
	 * or failing that, wrapping
	 * @param target
	 * @param collator
	 * @return
	 */
	public static <E, M> ParaReducer<E, M> paraReducer(Reduction<E, M> target, Reduction<M, M> collator) {
		return new ProxyParaReducer<E, M>(target, collator);
	}

	/**
	 * Return a new {@link Reduction} that narrows the inputs of the argument
	 * @param f
	 * @param reducer
	 * @return
	 */
	public static <E, F extends E, M> Reducer<F, M> narrow(Class<F> f, final Reduction<E, M> reducer) {
		return new NarrowingReducator<E, F, M>(f, reducer.m(), reducer.memoStart(), reducer);
	}
	
	/**
	 * Return a new {@link Reducer} that locates the minimum element of a {@link Collection}
	 * @param e
	 * @param cmp
	 * @return
	 */
	public static <E> Reducer<E, E> min(Class<E> e, Comparator<? super E> cmp) {
		return new ComparatorMin<E>(e, e, cmp);
	}
	
	/**
	 * Return a new {@link Reducer} that locates the minimum element of a {@link Collection}
	 * @param e
	 * @return
	 */
	public static <E extends Comparable<? super E>> Reducer<E, E> min(Class<E> e) {
		return new ComparableMin<E>(e, e);
	}

	/**
	 * Returns a new {@link Reducer} that locates the maximum element of a {@link Collection}
	 * @param e
	 * @param cmp
	 * @return
	 */
	public static <E> Reducer<E, E> max(Class<E> e, Comparator<? super E> cmp) {
		return new ComparatorMax<E>(e, e, cmp);
	}
	
	/**
	 * Returns a new {@link Reducer} that locates the maximum element of a {@link Collection}
	 * @param e
	 * @return
	 */
	public static <E extends Comparable<? super E>> Reducer<E, E> max(Class<E> e) {
		return new ComparableMax<E>(e, e);
	}
	
	private static class ComparableMax<E extends Comparable<? super E>> extends AbstractReducer<E, E> {
		private ComparableMax(Class<E> e, Class<E> m) {
			super(e, m, null);
		}

		@Override
		public E reduce0(E memo, E obj, Integer index) throws Exception {
			if(memo == null)
				return obj;
			if(memo.compareTo(obj) < 0)
				return obj;
			return memo;
		}
	}

	private static class ComparableMin<E extends Comparable<? super E>> extends AbstractReducer<E, E> {
		private ComparableMin(Class<E> e, Class<E> m) {
			super(e, m, null);
		}

		@Override
		public E reduce0(E memo, E obj, Integer index) throws Exception {
			if(memo == null)
				return obj;
			if(memo.compareTo(obj) > 0)
				return obj;
			return memo;
		}
	}

	private static class ComparatorMax<E> extends AbstractReducer<E, E> {
		private final Comparator<? super E> cmp;

		private ComparatorMax(Class<E> e, Class<E> m, Comparator<? super E> cmp) {
			super(e, m, null);
			this.cmp = cmp;
		}

		@Override
		public E reduce0(E memo, E obj, Integer index) throws Exception {
			if(memo == null)
				return obj;
			if(cmp.compare(memo, obj) < 0)
				return obj;
			return memo;
		}
	}

	private static class ComparatorMin<E> extends AbstractReducer<E, E> {
		private final Comparator<? super E> cmp;

		private ComparatorMin(Class<E> e, Class<E> m, Comparator<? super E> cmp) {
			super(e, m, null);
			this.cmp = cmp;
		}

		@Override
		public E reduce0(E memo, E obj, Integer index) throws Exception {
			if(memo == null)
				return obj;
			if(cmp.compare(memo, obj) > 0)
				return obj;
			return memo;
		}
	}

	private static class NarrowingReducator<E, F extends E, M> extends AbstractReducer<F, M> {
		private final Reduction<E, M> reducer;
	
		private NarrowingReducator(Class<F> e, Class<M> m, M memoStart,
				Reduction<E, M> reducer) {
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
