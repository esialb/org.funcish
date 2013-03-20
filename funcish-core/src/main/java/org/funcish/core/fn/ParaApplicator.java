package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaApplicator<E, V, T> extends Applicator<E, V, T> {
	public V over(Executor exec, Collection<? extends E> c);
}
