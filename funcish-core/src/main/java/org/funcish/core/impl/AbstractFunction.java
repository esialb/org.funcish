package org.funcish.core.impl;

import java.util.Arrays;

import org.funcish.core.Mappings;
import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mappicator;
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
		Mappicator<Class<?>, String> NAME = Mappings.classSimpleName();
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
