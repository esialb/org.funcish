package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyFunction<T> extends AbstractFunction<T> {

	private Function<T> target;
	
	public ProxyFunction(Function<T> target) {
		super(target.ret(), target.args());
		this.target = target;
	}

	@Override
	public T call(Object... args) throws Exception {
		return target.call(args);
	}

}
