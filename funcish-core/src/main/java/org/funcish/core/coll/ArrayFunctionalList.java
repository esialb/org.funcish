package org.funcish.core.coll;

import java.util.ArrayList;
import java.util.Collection;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.Sequences;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reducer;
import org.funcish.core.fn.Sequencator;

public class ArrayFunctionalList<E> extends ArrayList<E> implements FunctionalList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<E> e;
	
	public ArrayFunctionalList(Class<E> e) {
		this.e = e;
	}

	public ArrayFunctionalList(Class<E> e, Collection<? extends E> c) {
		super(c);
		this.e = e;
	}

	@Override
	public Class<E> e() {
		return e;
	}

	@Override
	public <V> FunctionalList<V> map(Mapping<? super E, V> m) {
		return Mappings.mappicator(m).map(this, new ArrayFunctionalList<V>(m.v()));
	}

	@Override
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into) {
		return Mappings.mappicator(m).map(this, into);
	}

	@Override
	public FunctionalList<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(p).filter(this, new ArrayFunctionalList<E>(e()));
	}

	@Override
	public <C extends Collection<? super E>> C filter(Predicate<? super E> p, C into) {
		return Predicates.predicator(p).filter(this, into);
	}

	@Override
	public <M> M reduce(Reducer<? super E, M> r) {
		return Reducers.reducator(r).reduce(this);
	}

	@Override
	public Sequencator<E> seq() {
		return Sequences.sequencator(e(), iterator());
	}

}
