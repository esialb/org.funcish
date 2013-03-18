package org.funcish.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.funcish.core.ann.MethodFunction;
import org.funcish.core.fn.Function;
import org.funcish.core.impl.CallableProxyFunction;
import org.funcish.core.impl.MethodProxyFunction;

public class Functions {
	
	public static <T> Function<T> fn(Callable<T> callable) {
		return new CallableProxyFunction<T>((Class) Object.class, callable);
	}
	
	public static <T> Function<T> fn(Class<T> ret, Callable<T> callable) {
		return new CallableProxyFunction<T>(ret, callable);
	}
	
	public static Function<?> fn(Object fnObj) {
		return fn(null, fnObj);
	}
	
	public static <T> Function<T> fn(Class<T> ret, Object fnObj) {
		Class<?> fnClass;
		
		fnClass = fnObj.getClass();
		while(fnClass != null) {
			Function<T> fn = annotatedFn(ret, fnClass, fnObj);
			if(fn != null)
				return fn;
			fnClass = fnClass.getSuperclass();
		}
	
		fnClass = fnObj.getClass();
		while(fnClass != null) {
			Function<T> fn = solitaryFn(ret, fnClass, fnObj);
			if(fn != null)
				return fn;
			fnClass = fnClass.getSuperclass();
		}
		
		throw new IllegalArgumentException("Unable to locate acceptable target function on " + fnObj.getClass());
	}

	private static <T> Function<T> annotatedFn(Class<T> ret, Class<?> fnClass, Object fnObj) {
		List<Method> candidates = new ArrayList<Method>();
		for(Method m : fnClass.getMethods()) {
			if(m.isAnnotationPresent(MethodFunction.class))
				candidates.add(m);
		}
		if(candidates.size() == 0)
			return null;
		if(candidates.size() > 1)
			throw new IllegalArgumentException("Class " + fnClass + " annotates " + candidates.size() + " methods as " + MethodFunction.class);
		Method m = candidates.get(0);
		m.setAccessible(true);
		if(ret == null)
			ret = (Class<T>) m.getReturnType();
		return new MethodProxyFunction<T>(ret, m, fnObj);
	}
	
	private static <T> Function<T> solitaryFn(Class<T> ret, Class<?> fnClass, Object fnObj) {
		List<Method> candidates = new ArrayList<Method>();
		for(Method m : fnClass.getMethods()) {
			if(ret == null || ret.isAssignableFrom(m.getReturnType()))
				candidates.add(m);
		}
		if(candidates.size() != 1)
			return null;
		Method m = candidates.get(0);
		m.setAccessible(true);
		if(ret == null)
			ret = (Class<T>) m.getReturnType();
		return new MethodProxyFunction<T>(ret, m, fnObj);
	}
	
	private Functions() {}
}
