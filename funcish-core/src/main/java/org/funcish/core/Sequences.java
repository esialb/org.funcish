package org.funcish.core;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducator;
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
	
	public static <E> Sequencator<E> sequencator(Class<E> e, Iterator<? extends E> in) {
		return new IteratorSequencator<E>(e, in);
	}
	
	public static <E, M> Sequencator<M> sequencator(Reducator<E, M> reducator, Iterator<? extends E> in) {
		return new ReducatorSequencator<E, M>(reducator.m(), reducator, in);
	}
	
	public static <E> Sequencator<E> widen(Class<E> e, final Sequencator<? extends E> sequence) {
		return sequencator(e, (Sequence<? extends E>) sequence);
	}
	
	public static <E> Sequence<E> widen(Class<E> e, final Sequence<? extends E> sequence) {
		return sequence(e, sequence);
	}
	
	public static Sequence<String> lines(Reader r) {
		final BufferedReader br = new BufferedReader(r); 
		return new AbstractSequence<String>(String.class) {
			@Override
			public String next0(Integer index) throws Exception {
				String line = br.readLine();
				if(line == null) {
					br.close();
					throw new NoSuchElementException();
				}
				return line;
			}
		};
	}
	
	private static class IteratorSequencator<E> extends AbstractSequencator<E> {
		private final Iterator<? extends E> in;

		private IteratorSequencator(Class<E> e, Iterator<? extends E> in) {
			super(e);
			this.in = in;
		}

		@Override
		public boolean hasNext0(Integer index) throws Exception {
			return in.hasNext();
		}

		@Override
		public E next0(Integer index) throws Exception {
			return in.next();
		}
	}

	private static class ReducatorSequencator<E, M> extends AbstractSequencator<M> {
		private final Reducator<E, M> reducator;
		private final Iterator<? extends E> in;
		private M last;

		private ReducatorSequencator(Class<M> e, Reducator<E, M> reducator, Iterator<? extends E> in) {
			super(e);
			this.reducator = reducator;
			this.in = in;
			last = reducator.memoStart();
		}

		@Override
		public boolean hasNext0(Integer index) throws Exception {
			return in.hasNext();
		}

		@Override
		public M next0(Integer index) throws Exception {
			return last = reducator.reduce(last, in.next(), index);
		}
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
