package org.funcish.core.fn;

/**
 * Type-safe transformational function from one object to another
 * @author robin
 *
 * @param <K>
 * @param <V>
 */
public interface Mapping<K, V> extends Function<V> {
	/**
	 * The class of the mapping inputs
	 * @return
	 */
	public Class<K> k();
	/**
	 * The class of the mapping outputs
	 * @return
	 */
	public Class<V> v();
	/**
	 * Return an output object from an input object at the argument index.
	 * {@code index} may be null.
	 * @param key
	 * @param index
	 * @return
	 */
	public V map(K key, Integer index);
}
