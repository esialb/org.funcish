/**
 * org.funcish: functional utilities for Java
 * 
 * Copyright 2013 Robin Kirkman.
 * 
 * Released under a BSD license.
 * 
 * Copyright (c) 2013, Robin Kirkman
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *  o  Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *  o  Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *  o  Neither the name of org.funcish nor the names of its contributors may be used to endorse 
 *     or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.funcish.core;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducer;
import org.funcish.core.fn.Sequencer;
import org.funcish.core.fn.Sequence;
import org.funcish.core.impl.AbstractSequencer;
import org.funcish.core.impl.AbstractSequence;
import org.funcish.core.impl.ProxySequencer;
import org.funcish.core.impl.ProxySequence;

public class Sequences {
	
	public static <E> Sequence<E> sequence(Class<E> e, Function<? extends E> target) {
		return new ProxySequence<E>(e, target);
	}
	
	public static <E> Sequencer<E> sequencer(Class<E> e, Sequence<? extends E> target) {
		if(target instanceof Sequencer<?>) {
			final Sequencer<? extends E> s = (Sequencer<? extends E>) target;
			return new WideningSequencator<E>(e, s);
		}
		return new ProxySequencer<E>(e, target);
	}
	
	public static <E> Sequencer<E> sequencer(Class<E> e, Iterator<? extends E> in) {
		return new IteratorSequencator<E>(e, in);
	}
	
	public static <E, M> Sequencer<M> sequencer(Reducer<E, M> reducator, Iterator<? extends E> in) {
		return new ReducatorSequencator<E, M>(reducator.m(), reducator, in);
	}
	
	public static <E> Sequencer<E> widen(Class<E> e, final Sequencer<? extends E> sequence) {
		return sequencer(e, (Sequence<? extends E>) sequence);
	}
	
	public static <E> Sequence<E> widen(Class<E> e, final Sequence<? extends E> sequence) {
		return sequence(e, sequence);
	}
	
	public static <E> Sequence<E> repeat(Class<E> e, final E val) {
		return new RepeatSequence<E>(e, val);
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
	
	private static class RepeatSequence<E> extends AbstractSequence<E> {
		private final E val;

		private RepeatSequence(Class<E> e, E val) {
			super(e);
			this.val = val;
		}

		@Override
		public E next0(Integer index) throws Exception {
			return val;
		}
	}

	private static class IteratorSequencator<E> extends AbstractSequencer<E> {
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

	private static class ReducatorSequencator<E, M> extends AbstractSequencer<M> {
		private final Reducer<E, M> reducator;
		private final Iterator<? extends E> in;
		private M last;

		private ReducatorSequencator(Class<M> e, Reducer<E, M> reducator, Iterator<? extends E> in) {
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

	private static class WideningSequencator<E> extends AbstractSequencer<E> {
		private final Sequencer<? extends E> s;
	
		private WideningSequencator(Class<E> e, Sequencer<? extends E> s) {
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
