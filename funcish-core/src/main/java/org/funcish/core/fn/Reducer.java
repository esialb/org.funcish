package org.funcish.core.fn;

public interface Reducer<E, M> extends Function<M> {
	public Class<E> e();
	public Class<M> m();
	public M memoStart();
	public M reduce(M memo, E obj, Integer index);
}
