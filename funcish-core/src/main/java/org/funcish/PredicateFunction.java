package org.funcish;

public interface PredicateFunction<T> extends Function<Boolean> {
	public boolean test(T value, Integer index);
}
