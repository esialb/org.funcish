package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyReducer<E, M> extends AbstractReducer<E, M> {

	private Function<M> target;
	
	public ProxyReducer(Class<E> e, M memoStart, Function<M> target) {
		super(e, target.ret(), memoStart);
		this.target = target;
	}

	@Override
	public M reduce(M memo, E obj, Integer index) throws Exception {
		return target.call(asArgs(target, memo, obj, index));
	}

}