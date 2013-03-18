package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyReducer<E, M> extends AbstractReducer<E, M> implements Proxied<Function<M>> {

	private Function<M> target;
	
	public ProxyReducer(Class<E> e, M memoStart, Function<M> target) {
		super(e, target.ret(), memoStart);
		this.target = target;
	}

	@Override
	public M reduce0(M memo, E obj, Integer index) throws Exception {
		return target.call(target.args(new Object[] {memo, obj, index}));
	}

	@Override
	public Function<M> proxiedTarget() {
		return target;
	}
}
