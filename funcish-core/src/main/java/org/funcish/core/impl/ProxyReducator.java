package org.funcish.core.impl;

import org.funcish.core.fn.Reducer;

public class ProxyReducator<E, M> extends AbstractReducator<E, M> implements Proxied<Reducer<E, M>> {

	private Reducer<E, M> target;
	
	public ProxyReducator(Reducer<E, M> target) {
		super(target.e(), target.m(), target.memoStart());
		this.target = target;
	}

	@Override
	public M reduce0(M memo, E obj, Integer index) throws Exception {
		return target.reduce0(memo, obj, index);
	}
	
	@Override
	public Reducer<E, M> proxiedTarget() {
		return target;
	}
}
