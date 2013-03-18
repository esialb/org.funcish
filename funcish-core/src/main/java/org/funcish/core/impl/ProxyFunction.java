package org.funcish.core.impl;

import java.util.concurrent.Callable;

public class ProxyFunction<T> extends AbstractFunction<T> {

	private Callable<T> target;
	
	public ProxyFunction(Class<T> ret, Callable<T> target) {
		super(ret, new Class<?>[0]);
		this.target = target;
	}

	@Override
	public T call(Object... args) throws Exception {
		return ret().cast(target.call());
	}

}
