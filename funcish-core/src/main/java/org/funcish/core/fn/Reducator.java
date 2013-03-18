package org.funcish.core.fn;

import java.util.Collection;

public interface Reducator<E, M> extends Reducer<E, M>, Applicator<E, M, M> {
	public M into(Collection<E> c, M into);
	
	public M reduce(Collection<E> c);
	public M reduce(Collection<E> c, M into);
}
