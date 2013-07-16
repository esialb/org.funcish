package org.funcish.core.fn;

public interface MultiMapper<K, V> extends MultiMapping<K, V>, Applicator<K, Iterable<V>, V[]> {
	public IntoIterable<V> map(Iterable<? extends K> c);
}
