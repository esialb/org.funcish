package org.funcish.core.fn;

/**
 * {@link Function} which tests input objects
 * @author robin
 *
 * @param <T>
 */
public interface Predicate<T> extends Function<Boolean> {
	/**
	 * The class of the input objects
	 * @return
	 */
	public Class<T> t();
	/**
	 * Test the input object.
	 * @param value The object to test
	 * @param index The index of the object being tested
	 * @return {@code true} to accept the object, {@code false} to reject
	 */
	public boolean test(T value, Integer index);
}
