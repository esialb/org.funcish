package org.funcish.core.impl;

import java.util.Collection;

import org.funcish.core.fn.Applicator;

public abstract class AbstractApplicator<E, T> extends AbstractFunction<T> implements Applicator<E, T, T> {

	public AbstractApplicator(Class<T> ret, Class<?>[] fnargs) {
		super(ret, fnargs);
	}

	@Override
	public T over(Collection<? extends E> c) {
		T ret = null;
		int index = 0;
		for(E e : c) {
			try {
				ret = call(args(new Object[] {e, index++}));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return ret;
	}
}
