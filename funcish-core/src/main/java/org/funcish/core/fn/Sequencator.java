package org.funcish.core.fn;

public interface Sequencator<E> extends Sequence<E>, AutoIterable<E> {
	
	public boolean hasNext(Integer index);

}
