package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaReducator<E, M> extends Reducator<E, M>, ParaApplicator<E, M, M> {
	public M into(Executor exec, Collection<E> c, M into);
}
