package org.funcish.core.coll;

import java.util.Set;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;


public interface FunctionalSet<E> extends FunctionalCollection<E>, Set<E> {
	@Override
	public <V> FunctionalSet<V> map(Mapping<? super E, V> m);
	@Override
	public FunctionalSet<E> filter(Predicate<? super E> p);
}
