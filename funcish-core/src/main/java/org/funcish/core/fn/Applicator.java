package org.funcish.core.fn;

import java.util.Collection;

/**
 * Interface for {@link Function}s that can apply themselves to a {@link Collection}
 * @author robin
 *
 * @param <E>
 * @param <V>
 * @param <T>
 */
public interface Applicator<E, V, T> extends Function<T> {
	/**
	 * Apply this function to the collection, returning some summary value or otherwise 
	 * transformed collection
	 * @param c
	 * @return
	 */
	public V over(Collection<? extends E> c);
}
