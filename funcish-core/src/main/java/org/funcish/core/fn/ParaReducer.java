package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

/**
 * Parallelized {@link Reducer}
 * @author robin
 *
 * @param <E>
 * @param <M>
 */
public interface ParaReducer<E, M> extends Reducer<E, M>, ParaApplicator<E, M, M> {
	/**
	 * {@link Reduction} used to combine the parallelized reduction results
	 * @return
	 */
	public Reduction<M, M> collator();
	
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
