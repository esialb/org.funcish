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

import java.util.Collection;

import org.funcish.core.fn.Mapper;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Predicator;
import org.funcish.core.fn.Reducer;
import org.funcish.core.fn.Reduction;
import org.funcish.core.fn.Sequencer;

/**
 * {@link Collection} with additional helper methods to process itself using
 * {@link Mapping}, {@link Predicate}, and {@link Reduction} functions.
 * @author robin
 *
 * @param <E>
 */
public interface FunctionalCollection<E> extends Collection<E> {
	
	public Class<E> e();
	
	/**
	 * Return a new {@link FunctionalCollection} obtained by applying
	 * the argument {@link Mapping} to this object.
	 * @param m
	 * @return
	 * @see Mapper#map(Collection)
	 */
	public <V> FunctionalCollection<V> map(Mapping<? super E, V> m);
	/**
	 * Apply the argument {@link Mapping} to this object, adding the results
	 * to argument {@link Collection}.
	 * @param m
	 * @param into
	 * @return
	 * @see Mapper#map(Collection, Collection)
	 */
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into);
	
	/**
	 * Return a new {@link FunctionalCollection} obtained by applying
	 * the argument {@link Predicate} to this object.
	 * @param p
	 * @return
	 * @see Predicator#filter(Collection)
	 */
	public FunctionalCollection<E> filter(Predicate<? super E> p);
	/**
	 * Apply the argument {@link Predicate} to this object, adding the results
	 * to the argument {@link Collection}
	 * @param p
	 * @param into
	 * @return
	 * @see Predicator#filter(Collection, Collection)
	 */
	public <C extends Collection<? super E>> C filter(Predicate<? super E> p, C into);
	
	/**
	 * Reduce this {@link FunctionalCollection}
	 * @param r
	 * @return
	 * @see Reducer#reduce(Collection)
	 */
	public <M> M reduce(Reduction<? super E, M> r);
	
	public Sequencer<E> seq();
}
