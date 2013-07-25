package org.funcish.core.impl;

import java.lang.reflect.Array;

import org.funcish.core.fn.MultiMapping;
import org.funcish.core.fn.MultiReceiver;

public abstract class AbstractMultiMapping<K, V> extends AbstractFunction<Void> implements MultiMapping<K, V> {

	private Class<K> k;
	private Class<V> v;
	
	public abstract void map0(K key, MultiReceiver<? super V> receiver, Integer index) throws Exception;
	
	public AbstractMultiMapping(Class<K> k, Class<V> v) {
		super(Void.class, new Class<?>[] {k, MultiReceiver.class, Integer.class});
		this.k = k;
		this.v = v;
	}

	@Override
	public Void call(Object... args) throws Exception {
		map(k.cast(args[0]), (MultiReceiver) args[1], (Integer) args[2]);
		return null;
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
	public void map(K key, MultiReceiver<? super V> receiver, Integer index) {
		try {
			map0(key, receiver, index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
