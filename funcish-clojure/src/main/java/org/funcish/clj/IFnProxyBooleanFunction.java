package org.funcish.clj;

import java.util.Collections;

import org.funcish.core.impl.AbstractFunction;
import org.funcish.core.impl.Proxied;

import clojure.lang.ArraySeq;
import clojure.lang.IFn;

public class IFnProxyBooleanFunction extends AbstractFunction<Boolean> implements Proxied<IFn> {
	private static Class<?>[] fnargs(int arity) {
		return Collections.nCopies(arity, Object.class).toArray(new Class[0]);
	}

	private IFn fn;
	private Integer arity;
	
	public IFnProxyBooleanFunction(IFn fn) {
		this(fn, null);
	}
	
	public IFnProxyBooleanFunction(IFn fn, Integer arity) {
		super(boolean.class, fnargs(arity == null ? 0 : arity));
		this.fn = fn;
		this.arity = arity;
	}

	@Override
	public Object[] args(Object[] values) {
		if(arity == null)
			return values;
		return super.args(values);
	}
	
	@Override
	public Boolean call(Object... args) throws Exception {
		Object ret = fn.applyTo(ArraySeq.create(args(args)));
		if(ret == null || ((ret instanceof Boolean) && !((Boolean) ret)))
			return false;
		return true;
	}

	@Override
	public IFn proxiedTarget() {
		return fn;
	}
}
