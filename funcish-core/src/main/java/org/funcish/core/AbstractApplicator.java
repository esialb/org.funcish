package org.funcish.core;

import java.util.Collection;

import org.funcish.Applicator;

public abstract class AbstractApplicator<E, T> extends AbstractFunction<T> implements Applicator<E, T, T> {

	public AbstractApplicator(Class<?>[] fnargs) {
		super(fnargs);
	}

	public T over(Collection<E> c) {
		T ret = null;
		int index = 0;
		for(E e : c) {
			try {
				ret = call(asArgs(e, index++));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return ret;
	}
}
