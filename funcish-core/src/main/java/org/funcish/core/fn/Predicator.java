package org.funcish.core.fn;

import java.util.Collection;

public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {
	public <C extends Collection<T>> C overInto(Collection<T> c, C into);
}
