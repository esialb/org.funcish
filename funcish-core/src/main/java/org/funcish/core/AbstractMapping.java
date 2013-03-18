package org.funcish.core;

import org.funcish.Mapping;

public abstract class AbstractMapping<K, V> extends AbstractFunction<V> implements Mapping<K, V> {

	private Class<K> k;
	private Class<V> v;
	
	public AbstractMapping(Class<K> k, Class<V> v) {
		super(new Class<?>[] {k, Integer.class});
		this.k = k;
		this.v = v;
	}
	
	public Class<K> getK() {
		return k;
	}
	
	public Class<V> getV() {
		return v;
	}
	
	public V call(Object... args) throws Exception {
		return v.cast(map(k.cast(args[0]), (Integer) args[1]));
	}
}
