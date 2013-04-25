package org.funcish.core.fn;

import java.util.concurrent.Callable;

public interface Fn<T> extends Callable<T> {
	/**
	 * Call this function
	 * @param args The arguments to the function
	 * @return The value of the function
	 * @throws Exception
	 */
	public T call(Object... args) throws Exception;

}
