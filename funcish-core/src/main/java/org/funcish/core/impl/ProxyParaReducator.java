package org.funcish.core.impl;

import org.funcish.core.fn.Reducer;

public class ProxyParaReducator<E, M> extends AbstractParaReducator<E, M> implements Proxied<Reducer<E, M>> {

	private Reducer<E, M> target;
	
	public ProxyParaReducator(Reducer<E, M> target, Reducer<M, M> collator) {
		super(target.e(), target.m(), target.memoStart(), collator);
		this.target = target;
	}

	@Override
	public M reduce0(M memo, E obj, Integer index) throws Exception {
		return target.reduce(memo, obj, index);
	}

	@Override
	public Reducer<E, M> proxiedTarget() {
		return target;
	}
}
