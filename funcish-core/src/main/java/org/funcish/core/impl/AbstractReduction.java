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

import org.funcish.core.fn.Reduction;

public abstract class AbstractReduction<E, M> extends AbstractFunction<M> implements Reduction<E, M> {

	private M memoStart;
	
	private Class<E> e;
	private Class<M> m;
	
	public abstract M reduce0(M memo, E obj, Integer index) throws Exception;
	
	public AbstractReduction(Class<E> e, Class<M> m, M memoStart) {
		super(m, new Class<?>[] {m, e, Integer.class});
		this.e = e;
		this.m = m;
		this.memoStart = memoStart;
	}
	
	@Override
	public M memoStart() {
		return memoStart;
	}
	
	@Override
	public Class<E> e() {
		return e;
	}
	
	@Override
	public Class<M> m() {
		return m;
	}

	@Override
	public M call(Object... args) throws Exception {
		return m.cast(reduce0(m.cast(args[0]), e.cast(args[1]), (Integer) args[2]));
	}
	
	@Override
	public M reduce(M memo, E obj, Integer index) {
		try {
			return reduce0(memo, obj, index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
