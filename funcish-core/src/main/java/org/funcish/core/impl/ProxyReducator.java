package org.funcish.core.impl;

import org.funcish.core.fn.Reducer;

public class ProxyReducator<E, M> extends AbstractReducator<E, M> {

	private Reducer<E, M> target;
	
	public ProxyReducator(Reducer<E, M> target) {
		super(target.e(), target.m(), target.memoStart());
		this.target = target;
	}

	@Override
	public M reduce(M memo, E obj, Integer index) throws Exception {
		return target.reduce(memo, obj, index);
	}
	
}
