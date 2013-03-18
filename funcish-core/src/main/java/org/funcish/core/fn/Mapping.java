package org.funcish.core.fn;

public interface Mapping<K, V> extends Function<V> {
	public Class<K> k();
	public Class<V> v();
	public V map(K key, Integer index);
	public V map0(K key, Integer index) throws Exception;
}
