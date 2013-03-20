package org.funcish.core.coll;

import java.util.Queue;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;

public interface FunctionalQueue<E> extends FunctionalCollection<E>, Queue<E> {
	@Override
	public <V> FunctionalQueue<V> map(Mapping<? super E, V> m);
	
	@Override
	public FunctionalQueue<E> filter(Predicate<? super E> p);
}
