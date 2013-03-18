package org.funcish;

public interface Predicate<T> extends Function<Boolean> {
	public boolean test(T value, Integer index) throws Exception;
}
