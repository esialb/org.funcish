package org.funcish.core.fn;

import java.util.Collection;

public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {
	public Collection<T> filter(Collection<? extends T> c);
	public <C extends Collection<T>> C filter(Collection<? extends T> c, C into);
}
