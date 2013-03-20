package org.funcish.core.coll;

import java.util.Collection;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reducer;

public interface FunctionalCollection<E> extends Collection<E> {
	public <V> FunctionalCollection<V> map(Mapping<? super E, V> m);
	public <V, C extends Collection<V>> C map(Mapping<? super E, V> m, C into);
	
	public FunctionalCollection<E> filter(Predicate<? super E> p);
	public <C extends Collection<E>> C filter(Predicate<? super E> p, C into);
	
	public <M> M reduce(Reducer<? super E, M> r);
}
