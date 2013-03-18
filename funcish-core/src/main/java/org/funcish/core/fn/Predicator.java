package org.funcish.core.fn;

import java.util.Collection;

public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {
	public <C extends Collection<T>> C into(Collection<T> c, C into);
	
	public Collection<T> filter(Collection<T> c);
	public <C extends Collection<T>> C filter(Collection<T> c, C into);
}
