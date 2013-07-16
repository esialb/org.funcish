package org.funcish.core.fn;

public interface MultiMapping<K, V> extends Function<V[]> {
	public Class<K> k();
	public Class<V> v();
	
	public void map(K key, MultiReceiver<V> receiver, Integer index);
}
