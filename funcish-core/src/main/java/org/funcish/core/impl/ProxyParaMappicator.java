package org.funcish.core.impl;

import org.funcish.core.fn.Mapping;

public class ProxyParaMappicator<K, V> extends AbstractParaMappicator<K, V> {

	private Mapping<K, V> target;
	
	public ProxyParaMappicator(Mapping<K, V> target) {
		super(target.k(), target.v());
		this.target = target;
	}
	
	@Override
	public V map0(K key, Integer index) throws Exception {
		return target.map0(key, index);
	}

}
