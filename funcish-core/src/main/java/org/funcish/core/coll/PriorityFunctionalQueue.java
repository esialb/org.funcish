package org.funcish.core.coll;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.funcish.core.Mappings;
import org.funcish.core.Predicates;
import org.funcish.core.Reducers;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reducer;

public class PriorityFunctionalQueue<E> extends PriorityQueue<E> implements FunctionalQueue<E> {

	public PriorityFunctionalQueue() {
	}

	public PriorityFunctionalQueue(Collection<? extends E> c) {
		super(c);
	}

	public PriorityFunctionalQueue(int initialCapacity, Comparator<? super E> comparator) {
		super(initialCapacity, comparator);
	}

	public PriorityFunctionalQueue(PriorityQueue<? extends E> c) {
		super(c);
	}
	
	@Override
	public <V> FunctionalQueue<V> map(Mapping<? super E, V> m) {
		return Mappings.mappicator(m).map(this, new PriorityFunctionalQueue<V>());
	}

	@Override
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into) {
		return Mappings.mappicator(m).map(this, into);
	}

	@Override
	public FunctionalQueue<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(p).filter(this, new PriorityFunctionalQueue<E>(0, comparator()));
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
