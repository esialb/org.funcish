package org.funcish.core.impl;

import org.funcish.core.fn.Reduction;

public class ProxyParaReducer<E, M> extends AbstractParaReducer<E, M> implements Proxied<Reduction<E, M>> {

	private Reduction<E, M> target;
	
	public ProxyParaReducer(Reduction<E, M> target, Reduction<M, M> collator) {
		super(target.e(), target.m(), target.memoStart(), collator);
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
