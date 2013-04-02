package org.funcish.core.impl;

import org.funcish.core.fn.Reduction;

public class ProxyReducer<E, M> extends AbstractReducer<E, M> implements Proxied<Reduction<E, M>> {

	private Reduction<E, M> target;
	
	public ProxyReducer(Reduction<E, M> target) {
		super(target.e(), target.m(), target.memoStart());
		this.target = target;
	}

	@Override
	public M reduce0(M memo, E obj, Integer index) throws Exception {
		return target.reduce(memo, obj, index);
	}
	
	@Override
	public Reduction<E, M> proxiedTarget() {
		return target;
	}
}
