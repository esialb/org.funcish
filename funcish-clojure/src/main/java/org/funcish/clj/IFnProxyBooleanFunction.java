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

package org.funcish.clj;

import java.util.Collections;

import org.funcish.core.impl.AbstractFunction;
import org.funcish.core.impl.Proxied;

import clojure.lang.ArraySeq;
import clojure.lang.IFn;

public class IFnProxyBooleanFunction extends AbstractFunction<Boolean> implements Proxied<IFn> {
	private static Class<?>[] fnargs(int arity) {
		return Collections.nCopies(arity, Object.class).toArray(new Class[0]);
	}

	private IFn fn;
	private Integer arity;
	
	public IFnProxyBooleanFunction(IFn fn) {
		this(fn, null);
	}
	
	public IFnProxyBooleanFunction(IFn fn, Integer arity) {
		super(boolean.class, fnargs(arity == null ? 0 : arity));
		this.fn = fn;
		this.arity = arity;
	}

	@Override
	public Object[] args(Object[] values) {
		if(arity == null)
			return values;
		return super.args(values);
	}
	
	@Override
	public Boolean call(Object... args) throws Exception {
		Object ret = fn.applyTo(ArraySeq.create(args(args)));
		if(ret == null || ((ret instanceof Boolean) && !((Boolean) ret)))
			return false;
		return true;
	}

	@Override
	public IFn proxiedTarget() {
		return fn;
	}
}
