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

package org.funcish.core.coll;

import java.util.Arrays;
import java.util.Collection;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.MultiMapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Reduction;
import org.funcish.core.fn.Sequencer;
import org.funcish.core.util.Iterables;
import org.funcish.core.util.Mappings;
import org.funcish.core.util.MultiMappings;
import org.funcish.core.util.Predicates;
import org.funcish.core.util.Reducers;
import org.funcish.core.util.Sequences;

public class ArrayFunctionalCollection<E> extends ArrayCollection<E> implements FunctionalCollection<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<E> e;
	
	public ArrayFunctionalCollection(Class<E> e) {
		this.e = e;
	}
	
	public ArrayFunctionalCollection(Class<E> e, E... c) {
		this(e, Arrays.asList(c));
	}

	public ArrayFunctionalCollection(Class<E> e, Collection<? extends E> c) {
		this(e);
		addAll(c);
	}
	
	@Override
	public boolean add(E e) {
		return super.add(e().cast(e));
	}

	@Override
	public Class<E> e() {
		return e;
	}

	@Override
	public <V> FunctionalCollection<V> map(Mapping<? super E, V> m) {
		return Mappings.mapper(m).map(this).into(new ArrayFunctionalCollection<V>(m.v()));
	}

	@Override
	public <V> FunctionalCollection<V> map(MultiMapping<? super E, V> m) {
		return MultiMappings.mapper(m).map(this).into(new ArrayFunctionalCollection<V>(m.v()));
	}
	
	@Override
	public FunctionalCollection<E> filter(Predicate<? super E> p) {
		return Predicates.predicator(e(), p).filter(this).into(new ArrayFunctionalCollection<E>(e()));
	}

	@Override
	public <M> M reduce(Reduction<? super E, M> r) {
		return Reducers.reducer(r).reduce(this);
	}

	@Override
	public Sequencer<E> seq() {
		return Sequences.sequencer(e(), iterator());
	}

	@Override
	public <C extends Collection<? super E>> C into(C dest) {
		dest.addAll(this);
		return dest;
	}

}
