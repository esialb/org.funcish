package org.funcish.core.impl;

import org.funcish.core.fn.Reduction;

public abstract class AbstractReduction<E, M> extends AbstractFunction<M> implements Reduction<E, M> {

	private M memoStart;
	
	private Class<E> e;
	private Class<M> m;
	
	public abstract M reduce0(M memo, E obj, Integer index) throws Exception;
	
	public AbstractReduction(Class<E> e, Class<M> m, M memoStart) {
		super(m, new Class<?>[] {m, e, Integer.class});
		this.e = e;
		this.m = m;
		this.memoStart = memoStart;
	}
	
	@Override
	public M memoStart() {
		return memoStart;
	}
	
	@Override
	public Class<E> e() {
		return e;
	}
	
	@Override
	public Class<M> m() {
		return m;
	}

	@Override
	public M call(Object... args) throws Exception {
		return m.cast(reduce0(m.cast(args[0]), e.cast(args[1]), (Integer) args[2]));
	}
	
	@Override
	public M reduce(M memo, E obj, Integer index) {
		try {
			return reduce0(memo, obj, index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
