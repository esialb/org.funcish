package org.funcish.core.impl;

import java.util.Iterator;

import org.funcish.core.fn.Sequence;

public abstract class AbstractSequence<E> extends AbstractFunction<E> implements Sequence<E> {

	private Class<E> e;
	
	public abstract E next0(Integer index) throws Exception;
	
	public AbstractSequence(Class<E> e) {
		super(e, new Class<?>[] {Integer.class});
		this.e = e;
	}

	@Override
	public E call(Object... args) throws Exception {
		return e.cast(next((Integer) args[0]));
	}

	@Override
	public Class<E> e() {
		return e;
	}

	@Override
	public E next(Integer index) {
		try {
			return next0(index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
