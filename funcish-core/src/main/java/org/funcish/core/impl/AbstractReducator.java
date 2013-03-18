package org.funcish.core.impl;

import java.util.Collection;

import org.funcish.core.fn.Reducator;

public abstract class AbstractReducator<E, M> extends AbstractReducer<E, M> implements Reducator<E, M> {

	private M memoStart;
	
	public AbstractReducator(Class<E> e, Class<M> m, M memoStart) {
		super(e, m);
		this.memoStart = memoStart;
	}

	protected M innerOver(M memo, Collection<E> c) {
		int index = 0;
		for(E e : c) {
			try {
				memo = reduce(memo, e, index++);
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return memo;
	}

	public M over(Collection<E> c) {
		return innerOver(memoStart, c);
	}
	
	public M memoStart() {
		return memoStart;
	}
	
	public M into(Collection<E> c, M into) {
		return innerOver(into, c);
	}
}
