package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyFunction<T> extends AbstractFunction<T> implements Proxied<Function<T>> {

	private Function<T> target;
	
	public ProxyFunction(Function<T> target) {
		super(target.ret(), target.args());
		this.target = target;
	}

	@Override
	public T call(Object... args) throws Exception {
		return target.call(args);
	}

	@Override
	public Function<T> proxiedTarget() {
		return target;
	}
}
