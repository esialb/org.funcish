package org.funcish.core.impl;

import java.lang.reflect.Method;

import org.funcish.core.util.Primitives;

public class MethodProxyFunction<T> extends AbstractFunction<T> {
	private Object target;
	private Method method;
	
	public MethodProxyFunction(Class<T> t, Method method, Object target) {
		super(Primitives.ensureNonPrimitive(t), method.getParameterTypes());
		this.target = target;
		this.method = method;
		if(!t.isAssignableFrom(method.getReturnType()))
			throw new IllegalArgumentException();
	}

	@Override
	public T call(Object... args) throws Exception {
		return ret().cast(method.invoke(target, args));
	}

	@Override
	protected String getClassName() {
		return method.getDeclaringClass().getName();
	}
}
