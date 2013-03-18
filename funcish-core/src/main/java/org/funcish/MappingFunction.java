package org.funcish;

public interface MappingFunction<K, V> extends Function<V> {
	public V map(K key, Integer index);
}
