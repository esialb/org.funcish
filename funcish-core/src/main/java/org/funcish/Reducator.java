package org.funcish;

public interface Reducator<E, M> extends Reducer<E, M>, Applicator<E, M, M> {
	public M memoStart();
}
