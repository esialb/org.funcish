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
	
	public static Class<?> ensureNonPrimitive(Class<?> possiblePrimitive) {
		if(wrappers.containsKey(possiblePrimitive))
			return wrappers.get(possiblePrimitive);
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
