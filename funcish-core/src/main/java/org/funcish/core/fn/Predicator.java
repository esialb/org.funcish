package org.funcish.core.fn;

import java.util.Collection;
/**
 * {@link Applicator} which applies a {@link Predicate} to a {@link Collection}
 * @author robin
 *
 * @param <T>
 */
public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {
	/**
	 * Return a new {@link Collection} containing only those elements of the argument
	 * {@link Collection} {@code c} that are accepted by this {@link Predicator}'s
	 * {@link #test(Object, Integer)} method
	 * @param c
	 * @return
	 */
	public Collection<T> filter(Collection<? extends T> c);
	/**
	 * Add to the argument {@link Collection} {@code into} those elements of the argument
	 * {@link Collection} {@code c} that are accepted by this {@link Predicator}'s
	 * {@link #test(Object, Integer)} method
	 * @param c
	 * @param into
	 * @return
	 */
	public <U extends T, C extends Collection<? super U>> C filter(Collection<? extends U> c, C into);
}
