package org.funcish.core.impl;

import java.util.Arrays;

import org.funcish.core.fn.Function;

public abstract class AbstractFunction<T> implements Function<T> {
	private Class<?>[] args;
	
	public AbstractFunction(Class<?>[] fnargs) {
		this.args = fnargs;
	}
	
	protected Object[] asArgs(Object... values) {
		if(values.length == args().length)
			return values;
		return Arrays.copyOf(values, args().length);
	}

	public T call() throws Exception {
		return call(asArgs(new Object[0]));
	}

	public Class<?>[] args() {
		return args;
	}
	
}
