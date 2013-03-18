package org.funcish.core.impl;

import org.funcish.core.fn.Predicate;

public abstract class AbstractPredicate<T> extends AbstractFunction<Boolean> implements Predicate<T> {

	private Class<T> t;
	
	public AbstractPredicate(Class<T> t) {
		super(Boolean.class, new Class<?>[] {t, Integer.class});
		this.t = t;
	}

	public Class<T> t() {
		return t;
	}

	public Boolean call(Object... args) throws Exception {
		return test0(t.cast(args[0]), (Integer) args[1]);
	}
	
	@Override
	public boolean test(T value, Integer index) {
		try {
			return test0(value, index);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
