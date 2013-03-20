package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaPredicator<T> extends Predicator<T>, ParaApplicator<T, Collection<T>, Boolean> {
	public Collection<T> filter(Executor exec, Collection<? extends T> c);
	public <U extends T, C extends Collection<U>> C filter(Executor exec, Collection<? extends U> c, C into);
}
