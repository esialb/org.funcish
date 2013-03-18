package org.funcish.core.fn;

import java.util.Collection;

public interface Applicator<E, V, T> extends Function<T> {
	public V over(Collection<E> c);
}
