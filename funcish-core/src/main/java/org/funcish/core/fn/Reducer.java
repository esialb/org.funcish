package org.funcish.core.fn;

import java.util.Collection;

/**
 * {@link Applicator} which can applies a {@link Reduction} to a {@link Collection}
 * @author robin
 *
 * @param <E>
 * @param <M>
 */
public interface Reducer<E, M> extends Reduction<E, M>, Applicator<E, M, M> {
	/**
	 * Reduce the argument {@link Collection} using the default initial value
	 * @param c
	 * @return
	 */
	public M reduce(Collection<? extends E> c);
	/**
	 * Reduce the argumetn {@link Collection} using the argument initial value
	 * @param c
	 * @param into
	 * @return
	 */
	public M reduce(Collection<? extends E> c, M into);
}
