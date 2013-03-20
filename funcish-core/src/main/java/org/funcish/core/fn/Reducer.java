package org.funcish.core.fn;

import java.util.Collection;

/**
 * {@link Function} to reduce an input collection to an output value
 * @author robin
 *
 * @param <E>
 * @param <M>
 */
public interface Reducer<E, M> extends Function<M> {
	/**
	 * The class of the input collection's elements
	 * @return
	 */
	public Class<E> e();
	/**
	 * The class of the output value
	 * @return
	 */
	public Class<M> m();
	/**
	 * The initial output value
	 * @return
	 */
	public M memoStart();
	/**
	 * Perform a step in the reduction of a {@link Collection}.
	 * @param memo The previous step's output value
	 * @param obj The object to consider
	 * @param index The object's index
	 * @return The new output value
	 */
	public M reduce(M memo, E obj, Integer index);
}
