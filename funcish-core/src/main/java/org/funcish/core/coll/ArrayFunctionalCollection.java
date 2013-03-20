package org.funcish.core.coll;

import java.util.Collection;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reducer;
import org.funcish.core.util.ArrayCollection;

public class ArrayFunctionalCollection<E> extends ArrayCollection<E> implements FunctionalCollection<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayFunctionalCollection() {
	}

	public ArrayFunctionalCollection(Collection<? extends E> c) {
		super(c);
	}

	@Override
	public <V> FunctionalCollection<V> map(Mapping<? super E, V> m) {
		return Mappings.mappicator(m).map(this, new ArrayFunctionalCollection<V>());
	}

	@Override
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into) {
		return Mappings.mappicator(m).map(this, into);
	}

	@Override
	public FunctionalCollection<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(p).filter(this, new ArrayFunctionalCollection<E>());
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
