package org.funcish.core.fn;

import java.util.Collection;

public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {
	public Collection<T> filter(Collection<T> c);
	public <C extends Collection<T>> C filter(Collection<T> c, C into);
}
