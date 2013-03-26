package org.funcish.core.impl;

import java.util.Iterator;

import org.funcish.core.fn.Sequencator;

public abstract class AbstractSequencator<E> extends AbstractSequence<E> implements Sequencator<E> {

	public abstract boolean hasNext0(Integer index) throws Exception;

	public AbstractSequencator(Class<E> e) {
		super(e);
	}

	@Override
	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return hasNext(null);
	}

	@Override
	public E next() {
		return next(null);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	
	@Override
	public boolean hasNext(Integer index) {
		try {
			return hasNext0(index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
