package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

/**
 * Parallelized {@link Reducator}
 * @author robin
 *
 * @param <E>
 * @param <M>
 */
public interface ParaReducator<E, M> extends Reducator<E, M>, ParaApplicator<E, M, M> {
	/**
	 * {@link Reducer} used to combine the parallelized reduction results
	 * @return
	 */
	public Reducer<M, M> collator();
	
	/**
	 * Reduce the input collection, using as the input memo the default initial memo for every step,
	 * submitting each step to the executor.  Results are then reduced again using the {@link #collator()}
	 * @param exec
	 * @param c
	 * @return
	 */
	public M reduce(Executor exec, Collection<? extends E> c);
	/**
	 * Reduce the input collection, using as the input memo the argument initial memo for every step,
	 * submitting each step to the executor.  Results are then reduced again using the {@link #collator()}
	 * @param exec
	 * @param c
	 * @param into
	 * @return
	 */
	public M reduce(Executor exec, Collection<? extends E> c, M into);
}
