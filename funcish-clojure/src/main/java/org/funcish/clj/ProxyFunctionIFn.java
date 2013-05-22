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

import java.util.Arrays;

import org.funcish.core.fn.Function;
import org.funcish.core.impl.ProxyFunction;

import clojure.lang.AFn;
import clojure.lang.IFn;
import clojure.lang.ISeq;
import clojure.lang.Util;

@SuppressWarnings("rawtypes")
public class ProxyFunctionIFn<T> extends ProxyFunction implements IFn {

	@SuppressWarnings("unchecked")
	public ProxyFunctionIFn(Function<T> target) {
		super(target);
	}

	@SuppressWarnings("unchecked")
	public Function<T> typed() {
		return (Function<T>) this;
	}
	
	protected Object[] underflow(Object... args) {
		for(int i = 0; i < args().length; i++) {
			Class<?> fc = args()[i];
			if(!fc.isPrimitive())
				continue;
			if(fc == byte.class)
				args[i] = ((Number) args[i]).byteValue();
			else if(fc == short.class)
				args[i] = ((Number) args[i]).shortValue();
			else if(fc == int.class)
				args[i] = ((Number) args[i]).intValue();
			else if(fc == long.class)
				args[i] = ((Number) args[i]).longValue();
			else if(fc == float.class)
				args[i] = ((Number) args[i]).floatValue();
			else if(fc == double.class)
				args[i] = ((Number) args[i]).doubleValue();
		}
		return args;
	}
	
	protected Object sneakyCall(Object... args) {
		try {
			return call(underflow(args));
		} catch (Exception ex) {
			throw Util.sneakyThrow(ex);
		}
	}

	@Override
	public void run() {
		sneakyCall();
	}

	@Override
	public Object invoke() {
		return sneakyCall();
	}

	@Override
	public Object invoke(Object arg1) {
		return sneakyCall(arg1);
	}

	@Override
	public Object invoke(Object arg1, Object arg2) {
		return sneakyCall(arg1, arg2);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3) {
		return sneakyCall(arg1, arg2, arg3);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4) {
		return sneakyCall(arg1, arg2, arg3, arg4);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13, Object arg14) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17, Object arg18) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17,
			Object arg18, Object arg19) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17,
			Object arg18, Object arg19, Object arg20) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17,
			Object arg18, Object arg19, Object arg20, Object... args) {
		Object[] first20 = new Object[] {arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20};
		Object[] all = Arrays.copyOf(first20, first20.length + args.length);
		System.arraycopy(args, 0, all, first20.length, args.length);
		return sneakyCall(all);
	}

	@Override
	public Object applyTo(ISeq arglist) {
		return AFn.applyToHelper(this, arglist);
	}

}
