package org.funcish.core.impl;

import org.funcish.core.fn.Mapping;

public class ProxyMapper<K, V> extends AbstractMapper<K, V> implements Proxied<Mapping<K, V>> {

	private Mapping<K, V> target;
	
	public ProxyMapper(Mapping<K, V> target) {
		super(target.k(), target.v());
		this.target = target;
	}

	@Override
	public V map0(K key, Integer index) throws Exception {
		return target.map(key, index);
	}

	@Override
	public Mapping<K, V> proxiedTarget() {
		return target;
	}
}
