package org.funcish;

public interface Mapping<K, V> extends Function<V> {
	public V map(K key, Integer index) throws Exception;
}
