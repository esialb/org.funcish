package org.funcish.core.fn;

import java.util.Collection;

public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {
	public Collection<T> filter(Collection<? extends T> c);
	public <U extends T, C extends Collection<U>> C filter(Collection<? extends U> c, C into);
}
