package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxyPredicate<T> extends AbstractPredicate<T> implements Proxied<Function<Boolean>> {

	private Function<Boolean> target;
	
	public ProxyPredicate(Class<T> t, Function<Boolean> target) {
		super(t);
		this.target = target;
	}

	@Override
	public boolean test0(T value, Integer index) throws Exception {
		return target.call(target.args(new Object[] {value, index}));
	}
	
	@Override
	public Function<Boolean> proxiedTarget() {
		return target;
	}

}
