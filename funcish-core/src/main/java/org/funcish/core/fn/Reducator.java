package org.funcish.core.fn;

import java.util.Collection;

public interface Reducator<E, M> extends Reducer<E, M>, Applicator<E, M, M> {
	public M memoStart();
	public M into(Collection<E> c, M into);
}
