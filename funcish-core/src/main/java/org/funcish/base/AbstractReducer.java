package org.funcish.base;

import org.funcish.Reducer;

public abstract class AbstractReducer<E, M> extends AbstractFunction<M> implements Reducer<E, M> {

	private Class<E> e;
	private Class<M> m;
	
	public AbstractReducer(Class<E> e, Class<M> m) {
		super(new Class<?>[] {e, m});
		this.e = e;
		this.m = m;
	}

	public M call(Object... args) throws Exception {
		return m.cast(reduce(m.cast(args[0]), e.cast(args[1]), (Integer) args[2]));
	}
}
