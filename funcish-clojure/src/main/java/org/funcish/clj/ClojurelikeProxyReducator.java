package org.funcish.clj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.funcish.core.fn.Function;
import org.funcish.core.impl.AbstractReducer;
import org.funcish.core.impl.Proxied;

public class ClojurelikeProxyReducator extends AbstractReducer<Object, Object> implements Proxied<Function<?>> {

	private Function<?> fn;
	
	public ClojurelikeProxyReducator(Function<?> fn) {
		super(Object.class, Object.class, null);
		this.fn = fn;
	}

	@Override
	public Object reduce0(Object memo, Object obj, Integer index)
			throws Exception {
		return fn.call(fn.args(new Object[] {memo, obj, index}));
	}
	
	@Override
	public Object over(Collection<?> c) {
		Iterator<?> i = c.iterator();
		Object first = i.next();
		Collection<Object> rest = new ArrayList<Object>();
		while(i.hasNext())
			rest.add(i.next());
		return innerOver(first, rest);
	}
	
	@Override
	public Function<?> proxiedTarget() {
		return fn;
	}

}
