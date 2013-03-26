package org.funcish.core.fn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Sequence<E> extends Function<E> {
	public Class<E> e();
	
	public E next(Integer index) throws NoSuchElementException;
}
