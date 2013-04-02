package org.funcish.core.fn;

import java.util.Collection;

/**
 * {@link Applicator} that applies a {@link Mapping} to a {@link Collection}
 * @author robin
 *
 * @param <K>
 * @param <V>
 */
public interface Mapper<K, V> extends Mapping<K, V>, Applicator<K, Collection<V>, V> {
	/**
	 * Return a new {@link Collection} containing the output values obtained by applying
	 * this {@link Mapper} to the input
	 * @param c
	 * @return
	 */
	public Collection<V> map(Collection<? extends K> c);
	/**
	 * Apply this {@link Mapper} to the input {@link Collection} {@code c}, and add
	 * the results to the output {@link Collection} {@code into}
	 * @param c
	 * @param into
	 * @return
	 */
	public <C extends Collection<? super V>> C map(Collection<? extends K> c, C into);
}
