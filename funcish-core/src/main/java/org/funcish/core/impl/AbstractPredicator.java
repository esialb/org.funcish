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

import org.funcish.core.coll.ArrayCollection;
import org.funcish.core.coll.ArrayFunctionalCollection;
import org.funcish.core.coll.FunctionalCollection;
import org.funcish.core.fn.IntoIterable;
import org.funcish.core.fn.Predicator;
import org.funcish.core.util.Predicates;

public abstract class AbstractPredicator<T> extends AbstractPredicate<T> implements Predicator<T> {

	public AbstractPredicator(Class<T> t) {
		super(t);
	}

	
	protected <U extends T, C extends Collection<? super U>> C innerOver(C out, Iterable<? extends U> c) {
		int index = 0;
		for(U e : c) {
			try {
				if(test0(t().cast(e), index++))
					out.add(e);
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return out;
	}

	@Override
	public FunctionalCollection<T> over(Iterable<? extends T> c) {
		return innerOver(new ArrayFunctionalCollection<T>(t()), c);
	}

	@Override
	public IntoIterable<T> filter(Iterable<? extends T> c) {
		return over(c);
	}


	@Override
	public Predicator<T> and(Predicator<T>... preds) {
		return Predicates.and(this, preds);
	}


	@Override
	public Predicator<T> or(Predicator<T>... preds) {
		return Predicates.or(this, preds);
	}


	@Override
	public Predicator<T> not() {
		return Predicates.not(this);
	}


	@Override
	public <U extends T> Predicator<U> narrow(Class<U> u) {
		return Predicates.narrow(u, this);
	}
}
