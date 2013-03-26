package org.funcish.core.impl;

import org.funcish.core.fn.Function;

public class ProxySequence<E> extends AbstractSequence<E> {
	
	private Function<? extends E> target;
	
	public ProxySequence(Class<E> e, Function<? extends E> target) {
		super(e);
		this.target = target;
	}

	@Override
	public E next0(Integer index) throws Exception {
		return target.call(target.args(new Object[] {index}));
	}
}
