package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyPredicate<T> extends AbstractPredicate<T> {

	private Function<Boolean> target;
	
	public ProxyPredicate(Class<T> t, Function<Boolean> target) {
		super(t);
		this.target = target;
	}

	@Override
	public boolean test(T value, Integer index) throws Exception {
		return target.call(asArgs(target, value, index));
	}

}
