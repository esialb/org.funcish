package org.funcish.core.impl;

import org.funcish.core.fn.Mapping;

public abstract class AbstractMapping<K, V> extends AbstractFunction<V> implements Mapping<K, V> {

	private Class<K> k;
	private Class<V> v;
	
	public AbstractMapping(Class<K> k, Class<V> v) {
		super(v, new Class<?>[] {k, Integer.class});
		this.k = k;
		this.v = v;
	}
	
	@Override
	public Class<K> k() {
		return k;
	}
	
	@Override
	public Class<V> v() {
		return v;
	}
	
	@Override
	public V call(Object... args) throws Exception {
		return v.cast(map0(k.cast(args[0]), (Integer) args[1]));
	}
	
	@Override
	public V map(K key, Integer index) {
		try {
			return map0(key, index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
