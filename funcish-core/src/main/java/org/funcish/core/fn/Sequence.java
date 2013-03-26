package org.funcish.core.fn;

import java.util.Iterator;

public interface Sequence<E> extends Function<E> {
	public Class<E> e();
	
	public E next(Integer index);
}
