package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyMapping<K, V> extends AbstractMapping<K, V> {
	private Function<V> target;
	
	public ProxyMapping(Class<K> k, Function<V> target) {
		super(k, target.ret());
		this.target = target;
	}

	@Override
	public V map(K key, Integer index) throws Exception {
		return target.call(asArgs(target, key, index));
	}
}
