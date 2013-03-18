package org.funcish.core.fn;

public interface Reducer<E, M> extends Function<M> {
	public M reduce(M memo, E obj, Integer index) throws Exception;
}
