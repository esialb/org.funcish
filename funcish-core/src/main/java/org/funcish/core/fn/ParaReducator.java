package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaReducator<E, M> extends Reducator<E, M>, ParaApplicator<E, M, M> {
	public Reducer<M, M> collator();
	public M into(Executor exec, Collection<E> c, M into);
	
	public M reduce(Executor exec, Collection<E> c);
	public M reduce(Executor exec, Collection<E> c, M into);
}
