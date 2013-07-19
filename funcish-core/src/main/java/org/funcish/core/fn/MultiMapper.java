package org.funcish.core.fn;

public interface MultiMapper<K, V> extends MultiMapping<K, V>, Applicator<K, IntoIterable<V>, Void> {
	public IntoIterable<V> map(Iterable<? extends K> c);
}
