package org.funcish.core.coll;

import java.util.Collection;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.Sequences;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reduction;
import org.funcish.core.fn.Sequencer;
import org.funcish.core.util.ArrayCollection;

public class ArrayFunctionalCollection<E> extends ArrayCollection<E> implements FunctionalCollection<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<E> e;
	
	public ArrayFunctionalCollection(Class<E> e) {
		this.e = e;
	}

	public ArrayFunctionalCollection(Class<E> e, Collection<? extends E> c) {
		super(c);
		this.e = e;
	}

	@Override
	public Class<E> e() {
		return e;
	}

	@Override
	public <V> FunctionalCollection<V> map(Mapping<? super E, V> m) {
		return Mappings.mapper(m).map(this, new ArrayFunctionalCollection<V>(m.v()));
	}

	@Override
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into) {
		return Mappings.mapper(m).map(this, into);
	}

	@Override
	public FunctionalCollection<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(p).filter(this, new ArrayFunctionalCollection<E>(e()));
	}

	@Override
	public <C extends Collection<? super E>> C filter(Predicate<? super E> p, C into) {
		return Predicates.predicator(p).filter(this, into);
	}

	@Override
	public <M> M reduce(Reduction<? super E, M> r) {
		return Reducers.reducer(r).reduce(this);
	}

	@Override
	public Sequencer<E> seq() {
		return Sequences.sequencer(e(), iterator());
	}

}
