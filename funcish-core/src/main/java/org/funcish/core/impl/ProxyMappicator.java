package org.funcish.core.impl;

import org.funcish.core.fn.Mapping;

public class ProxyMappicator<K, V> extends AbstractMappicator<K, V> implements Proxied<Mapping<K, V>> {

	private Mapping<K, V> target;
	
	public ProxyMappicator(Mapping<K, V> target) {
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
