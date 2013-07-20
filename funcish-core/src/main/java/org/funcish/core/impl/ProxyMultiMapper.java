package org.funcish.core.impl;

import org.funcish.core.fn.MultiMapper;
import org.funcish.core.fn.MultiMapping;
import org.funcish.core.fn.MultiReceiver;

public class ProxyMultiMapper<K, V> extends AbstractMultiMapper<K, V> implements Proxied<MultiMapping<K, V>> {

	private MultiMapping<K, V> m;
	
	public ProxyMultiMapper(MultiMapping<K, V> m) {
		super(m.k(), m.v());
		this.m = m;
	}

	@Override
	public void map0(K key, MultiReceiver<? super V> receiver, Integer index)
			throws Exception {
		m.map(key, receiver, index);
	}

	@Override
	public MultiMapping<K, V> proxiedTarget() {
		return m;
	}
	
}
