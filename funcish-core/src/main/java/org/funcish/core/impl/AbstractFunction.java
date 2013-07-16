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

import java.util.Arrays;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mapper;
import org.funcish.core.util.Mappings;
import org.funcish.core.util.Strings;

public abstract class AbstractFunction<T> implements Function<T> {
	
	private Class<T> ret;
	private Class<?>[] args;
	
	public AbstractFunction(Class<T> ret, Class<?>[] fnargs) {
		this.ret = ret;
		this.args = fnargs;
	}
	
	@Override
	public Object[] args(Object[] values) {
		if(values.length == args().length)
			return values;
		return Arrays.copyOf(values, args().length);
	}

	@Override
	public T call() throws Exception {
		return call(args(new Object[0]));
	}

	@Override
	public Class<T> ret() {
		return ret;
	}
	
	@Override
	public Class<?>[] args() {
		return args;
	}
	
	protected String getClassName() {
		Object fn = this;
		while(fn instanceof Proxied<?>)
			fn = ((Proxied<?>) fn).proxiedTarget();
		return fn.getClass().getName();
	}
	
	@Override
	public String toString() {
		Mapper<Class<?>, String> NAME = Mappings.classSimpleName();
		StringBuilder sb = new StringBuilder();
		sb.append("fn://");
		sb.append(getClassName());
		sb.append("/");
		sb.append(NAME.map(ret(), null));
		sb.append("(");
		sb.append(Strings.join(",", NAME.map(Arrays.asList(args()))));
		sb.append(")");
		return sb.toString();
	}
}
