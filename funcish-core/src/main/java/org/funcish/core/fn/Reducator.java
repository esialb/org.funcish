package org.funcish.core.fn;

import java.util.Collection;

public interface Reducator<E, M> extends Reducer<E, M>, Applicator<E, M, M> {
	public M reduce(Collection<? extends E> c);
	public M reduce(Collection<? extends E> c, M into);
}
