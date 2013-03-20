package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

/**
 * Provides parallelized {@link Mappicator} functionality
 * @author robin
 *
 * @param <K>
 * @param <V>
 */
public interface ParaMappicator<K, V> extends Mappicator<K, V>, ParaApplicator<K, Collection<V>, V> {
	/**
	 * Apply this {@link ParaMappicator} to the input {@link Collection} {@code c}, with
	 * each application in a separate job submitted to the {@link Executor} {@code exec},
	 * returning the results as a new {@link Collection}
	 * @param exec
	 * @param c
	 * @return
	 */
	public Collection<V> map(Executor exec, Collection<? extends K> c);
	/**
	 * Apply this {@link ParaMappicator} to the input {@link Collection} {@code c} with each
	 * application in a separate job submitted to the {@link Executor} {@code exec},
	 * adding the results to the output {@link Collection} {@code into}
	 * @param exec
	 * @param c
	 * @param into
	 * @return
	 */
	public <C extends Collection<? super V>> C map(Executor exec, Collection<? extends K> c, C into);
}
