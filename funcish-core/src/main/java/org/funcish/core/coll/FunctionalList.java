package org.funcish.core.coll;

import java.util.List;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;

public interface FunctionalList<E> extends FunctionalCollection<E>, List<E> {
	@Override
	public <V> FunctionalList<V> map(Mapping<? super E, V> m);
	
	@Override
	public FunctionalList<E> filter(Predicate<? super E> p);
}
