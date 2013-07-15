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

package org.funcish.core.impl;

import java.util.Collection;

import org.funcish.core.fn.Mapper;
import org.funcish.core.util.ArrayCollection;

public abstract class AbstractMapper<K, V> extends AbstractMapping<K, V> implements Mapper<K, V> {

	public AbstractMapper(Class<K> k, Class<V> v) {
		super(k, v);
	}
	
	protected <C extends Collection<? super V>> C innerOver(C out, Iterable<? extends K> c) {
		int index = 0;
		for(K e : c) {
			try {
				out.add(v().cast(map0(k().cast(e), index++)));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return out;
	}

	@Override
	public Collection<V> over(Iterable<? extends K> c) {
		return innerOver(new ArrayCollection<V>(), c);
	}
	
	@Override
	public Iterable<V> map(Iterable<? extends K> c) {
		return over(c);
	}
}
