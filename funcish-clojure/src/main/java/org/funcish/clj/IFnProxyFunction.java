package org.funcish.clj;

import java.util.Collections;

import org.funcish.core.impl.AbstractFunction;
import org.funcish.core.impl.Proxied;

import clojure.lang.ArraySeq;
import clojure.lang.IFn;
import clojure.lang.RestFn;

public class IFnProxyFunction extends AbstractFunction<Object> implements Proxied<IFn> {
	private static Class<?>[] fnargs(int arity) {
		return Collections.nCopies(arity, Object.class).toArray(new Class[0]);
	}

	private IFn fn;
	private Integer arity;
	
	public IFnProxyFunction(IFn fn) {
		this(fn, null);
	}
	
	public IFnProxyFunction(IFn fn, Integer arity) {
		super(Object.class, fnargs(arity == null ? 0 : arity));
		this.fn = fn;
		this.arity = arity;
	}

	@Override
	public Object[] args(Object... values) {
		if(arity == null)
			return values;
		return super.args(values);
	}
	
	@Override
	public Object call(Object... args) throws Exception {
		return fn.applyTo(ArraySeq.create(args(args)));
	}

	@Override
	public IFn proxiedTarget() {
		return fn;
	}
}
