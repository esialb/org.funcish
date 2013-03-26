package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Sequencator;
import org.funcish.core.fn.Sequence;
import org.funcish.core.impl.AbstractSequencator;
import org.funcish.core.impl.AbstractSequence;
import org.funcish.core.impl.ProxySequencator;
import org.funcish.core.impl.ProxySequence;

public class Sequences {
	
	public static <E> Sequence<E> sequence(Class<E> e, Function<? extends E> target) {
		return new ProxySequence<E>(e, target);
	}
	
	public static <E> Sequencator<E> sequencator(Class<E> e, Sequence<? extends E> target) {
		if(target instanceof Sequencator<?>) {
			final Sequencator<? extends E> s = (Sequencator<? extends E>) target;
			return new WideningSequencator<E>(e, s);
		}
		return new ProxySequencator<E>(e, target);
	}
	
	public static <E> Sequencator<E> widen(Class<E> e, final Sequencator<? extends E> sequence) {
		return sequencator(e, sequence);
	}
	
	public static <E> Sequence<E> widen(Class<E> e, final Sequence<? extends E> sequence) {
		return sequence(e, sequence);
	}
	
	private static class WideningSequencator<E> extends AbstractSequencator<E> {
		private final Sequencator<? extends E> s;
	
		private WideningSequencator(Class<E> e, Sequencator<? extends E> s) {
			super(e);
			this.s = s;
		}
	
		@Override
		public boolean hasNext0(Integer index) throws Exception {
			return s.hasNext(index);
		}
	
		@Override
		public E next0(Integer index) throws Exception {
			return s.next(index);
		}
	}

	private Sequences() {}
}
