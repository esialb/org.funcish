package org.funcish.core.fn;

import java.util.concurrent.Callable;

public interface Function<T> extends Callable<T> {
	public Class<?>[] args();
	public Class<T> ret();
	public T call(Object... args) throws Exception;
}
