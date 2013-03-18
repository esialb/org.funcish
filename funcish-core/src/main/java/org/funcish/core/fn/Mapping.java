package org.funcish.core.fn;

public interface Mapping<K, V> extends Function<V> {
	public V map(K key, Integer index) throws Exception;
}
