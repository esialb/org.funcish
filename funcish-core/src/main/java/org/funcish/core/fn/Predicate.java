package org.funcish.core.fn;

public interface Predicate<T> extends Function<Boolean> {
	public Class<T> t();
	public boolean test(T value, Integer index);
}
