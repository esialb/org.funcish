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

package org.funcish.core.util;

import java.util.HashMap;
import java.util.Map;

public class Primitives {
	public static Class<?> toWrapperClass(Class<?> primitiveClass) {
		return wrappers.get(primitiveClass);
	}
	
	public static Class<?> toPrimitiveClass(Class<?> wrapperClass) {
		return primitives.get(wrapperClass);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> ensureNonPrimitive(Class<T> possiblePrimitive) {
		if(wrappers.containsKey(possiblePrimitive))
			return (Class<T>) wrappers.get(possiblePrimitive);
		return possiblePrimitive;
	}
	
	private static Map<Class<?>, Class<?>> wrappers = new HashMap<Class<?>, Class<?>>();
	private static Map<Class<?>, Class<?>> primitives = new HashMap<Class<?>, Class<?>>();
	static {
		pw(boolean.class, Boolean.class);
		pw(byte.class, Byte.class);
		pw(short.class, Short.class);
		pw(char.class, Character.class);
		pw(int.class, Integer.class);
		pw(long.class, Long.class);
		pw(float.class, Float.class);
		pw(double.class, Double.class);
		pw(void.class, Void.class);
	}
	
	private static void pw(Class<?> primitive, Class<?> wrapper) {
		wrappers.put(primitive, wrapper);
		primitives.put(wrapper, primitive);
	}
	
}
