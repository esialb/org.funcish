package org.funcish.core.coll;

import java.util.Collection;
import java.util.HashSet;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reducer;

public class HashFunctionalSet<E> extends HashSet<E> implements FunctionalSet<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashFunctionalSet() {
	}

	public HashFunctionalSet(Collection<? extends E> c) {
		super(c);
	}

	@Override
	public <V> FunctionalSet<V> map(Mapping<? super E, V> m) {
		return Mappings.mappicator(m).map(this, new HashFunctionalSet<V>());
	}

	@Override
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into) {
		return Mappings.mappicator(m).map(this, into);
	}

	@Override
	public FunctionalSet<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(p).filter(this, new HashFunctionalSet<E>());
	}

	@Override
	public <C extends Collection<? super E>> C filter(Predicate<? super E> p, C into) {
		return Predicates.predicator(p).filter(this, into);
	}

	@Override
	public <M> M reduce(Reducer<? super E, M> r) {
		return Reducers.reducator(r).reduce(this);
	}

}
