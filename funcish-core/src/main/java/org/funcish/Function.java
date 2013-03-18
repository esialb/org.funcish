package org.funcish;

import java.util.concurrent.Callable;

public interface Function<T> extends Callable<T> {
	public T call(Object... args) throws Exception;
}
