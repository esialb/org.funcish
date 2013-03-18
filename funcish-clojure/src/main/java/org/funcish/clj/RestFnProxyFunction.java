package org.funcish.clj;

import java.util.Collections;

import org.funcish.core.impl.AbstractFunction;

import clojure.lang.ArraySeq;
import clojure.lang.RestFn;

public class RestFnProxyFunction extends AbstractFunction<Object> {
	private static Class<?>[] fnargs(int arity) {
		return Collections.nCopies(arity, Object.class).toArray(new Class[0]);
	}

	private RestFn fn;
	
	public RestFnProxyFunction(RestFn fn) {
		super(Object.class, fnargs(fn.getRequiredArity()));
		this.fn = fn;
	}

	@Override
	public Object call(Object... args) throws Exception {
		return fn.applyTo(ArraySeq.create(args));
	}

}
