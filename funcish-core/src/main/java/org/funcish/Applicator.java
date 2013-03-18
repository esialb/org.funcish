package org.funcish;

import java.util.Collection;

public interface Applicator<E, V, T> extends Function<T> {
	public V over(Collection<E> c);
}
