package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Sequencator;
import org.funcish.core.fn.Sequence;
import org.funcish.core.impl.AbstractSequencator;
import org.funcish.core.impl.AbstractSequence;
import org.funcish.core.impl.ProxySequencator;
import org.funcish.core.impl.ProxySequence;

public class Sequences {
	
	public static <E> Sequence<E> sequence(Class<E> e, Function<E> target) {
		return new ProxySequence<E>(e, target);
	}
	
	public static <E> Sequencator<E> sequencator(Class<E> e, Sequence<E> target) {
		if(target instanceof Sequencator<?>)
			return (Sequencator<E>) target;
		return new ProxySequencator<E>(e, target);
	}
	
	public static <E> Sequencator<E> widen(Class<E> e, final Sequencator<? extends E> sequence) {
		return new AbstractSequencator<E>(e) {
			@Override
			public boolean hasNext0(Integer index) throws Exception {
				return sequence.hasNext(index);
			}

			@Override
			public E next0(Integer index) throws Exception {
				return sequence.next(index);
			}
		};
	}
	
	public static <E> Sequence<E> widen(Class<E> e, final Sequence<? extends E> sequence) {
		return new ProxySequence<E>(e, sequence);
	}
	
	private Sequences() {}
}
