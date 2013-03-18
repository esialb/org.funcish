package org.funcish.base;

import org.funcish.Predicate;

public abstract class AbstractPredicate<T> extends AbstractFunction<Boolean> implements Predicate<T> {

	private Class<T> t;
	
	public AbstractPredicate(Class<T> t) {
		super(new Class<?>[] {t, Integer.class});
		this.t = t;
	}

	public Class<T> getT() {
		return t;
	}

	public Boolean call(Object... args) throws Exception {
		return test(t.cast(args[0]), (Integer) args[1]);
	}
}
