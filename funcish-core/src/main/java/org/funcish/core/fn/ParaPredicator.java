package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

/**
 * Parallelized {@link Predicator}
 * @author robin
 *
 * @param <T>
 */
public interface ParaPredicator<T> extends Predicator<T>, ParaApplicator<T, Collection<T>, Boolean> {
	/**
	 * Return a new {@link Collection} containing those objects of the input {@link Collection}
	 * {@code c} that are accepted by this {@link ParaPredicator}'s {@link #test(Object, Integer)} method
	 * @param exec
	 * @param c
	 * @return
	 */
	public Collection<T> filter(Executor exec, Collection<? extends T> c);
	/**
	 * Add those objects of the input {@link Collection} {@code c} that are accepted by this {@link ParaPredicator}'s
	 * {@link #test(Object, Integer)} method to the argument {@link Collection} {@code into} 
	 * @param exec
	 * @param c
	 * @param into
	 * @return
	 */
	public <U extends T, C extends Collection<? super U>> C filter(Executor exec, Collection<? extends U> c, C into);
}
