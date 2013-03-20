package org.funcish.core.coll;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;

public interface FunctionalDeque<E> extends FunctionalQueue<E> {
	@Override
	public <V> FunctionalDeque<V> map(Mapping<? super E, V> m);
	
	@Override
	public FunctionalDeque<E> filter(Predicate<? super E> p);
}
