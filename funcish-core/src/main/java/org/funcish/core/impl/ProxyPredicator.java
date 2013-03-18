package org.funcish.core.impl;

import org.funcish.core.fn.Predicate;

public class ProxyPredicator<T> extends AbstractPredicator<T> {

	private Predicate<T> target;
	
	public ProxyPredicator(Predicate<T> target) {
		super(target.t());
		this.target = target;
	}

	@Override
	public boolean test0(T value, Integer index) throws Exception {
		return target.test0(value, index);
	}

}
