package org.funcish.core.coll;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.Sequences;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reduction;
import org.funcish.core.fn.Sequencer;

public class PriorityFunctionalQueue<E> extends PriorityQueue<E> implements FunctionalQueue<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<E> e;
	
	public PriorityFunctionalQueue(Class<E> e) {
		this.e = e;
	}

	public PriorityFunctionalQueue(Class<E> e, Collection<? extends E> c) {
		super(c);
		this.e = e;
	}

	public PriorityFunctionalQueue(Class<E> e, int initialCapacity, Comparator<? super E> comparator) {
		super(initialCapacity, comparator);
		this.e = e;
	}

	public PriorityFunctionalQueue(Class<E> e, PriorityQueue<? extends E> c) {
		super(c);
		this.e = e;
	}
	
	@Override
	public Class<E> e() {
		return e;
	}
	
	@Override
	public <V> FunctionalQueue<V> map(Mapping<? super E, V> m) {
		return Mappings.mapper(m).map(this, new PriorityFunctionalQueue<V>(m.v()));
	}
	
	public <V> FunctionalQueue<V> map(Mapping<? super E, V> m, Comparator<? super V> cmp) {
		return Mappings.mapper(m).map(this, new PriorityFunctionalQueue<V>(m.v(), size(), cmp));
	}

	@Override
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into) {
		return Mappings.mapper(m).map(this, into);
	}

	@Override
	public FunctionalQueue<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(p).filter(this, new PriorityFunctionalQueue<E>(e(), 0, comparator()));
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
