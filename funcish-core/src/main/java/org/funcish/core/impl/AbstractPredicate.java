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

import org.funcish.core.fn.Predicate;

public abstract class AbstractPredicate<T> extends AbstractFunction<Boolean> implements Predicate<T> {

	private Class<T> t;
	
	public abstract boolean test0(T value, Integer index) throws Exception;
	
	public AbstractPredicate(Class<T> t) {
		super(Boolean.class, new Class<?>[] {t, Integer.class});
		this.t = t;
	}

	@Override
	public Class<T> t() {
		return t;
	}

	@Override
	public Boolean call(Object... args) throws Exception {
		return test0(t.cast(args[0]), (Integer) args[1]);
	}
	
	@Override
	public boolean test(T value, Integer index) {
		try {
			return test0(value, index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
