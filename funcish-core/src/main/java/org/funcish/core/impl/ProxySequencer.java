package org.funcish.core.impl;

import java.util.NoSuchElementException;

import org.funcish.core.fn.Sequence;

public class ProxySequencer<E> extends AbstractSequencer<E> {
	
	private Sequence<? extends E> target;
	
	private Boolean hasNext = null;
	private E next;
	
	public ProxySequencer(Class<E> e, Sequence<? extends E> target) {
		super(e);
		this.target = target;
	}

	@Override
	public boolean hasNext0(Integer index) throws Exception {
		if(hasNext == null) {
			try {
				next = target.next(index);
				hasNext = true;
			} catch(NoSuchElementException nsee) {
				hasNext = false;
			}
		}
		return hasNext;
	}

	@Override
	public E next0(Integer index) throws Exception {
		if(!hasNext())
			throw new NoSuchElementException();
		hasNext = null;
		return next;
	}

	
}
