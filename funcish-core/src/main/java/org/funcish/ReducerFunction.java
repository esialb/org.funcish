package org.funcish;

public interface ReducerFunction<E, M> extends Function<M> {
	public M reduce(M memo, E obj, Integer index);
}
