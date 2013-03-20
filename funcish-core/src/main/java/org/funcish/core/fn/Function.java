package org.funcish.core.fn;

import java.util.concurrent.Callable;

/**
 * Base interface for any function
 * @author robin
 *
 * @param <T>
 */
public interface Function<T> extends Callable<T> {
	/**
	 * Returns the classes of the arguments to this function.
	 * @return
	 */
	public Class<?>[] args();
	/**
	 * Returns the return type of this function
	 * @return
	 */
	public Class<T> ret();
	/**
	 * Convert the argument object array into an array of objects suitable
	 * as arguments for this function, such as by trimming or padding
	 * @param args
	 * @return
	 */
	public Object[] args(Object[] args);
	/**
	 * Call this function
	 * @param args The arguments to the function
	 * @return The value of the function
	 * @throws Exception
	 */
	public T call(Object... args) throws Exception;
}
