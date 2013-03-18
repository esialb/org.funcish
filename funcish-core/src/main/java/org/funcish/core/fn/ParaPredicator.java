package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaPredicator<T> extends Predicator<T>, ParaApplicator<T, Collection<T>, Boolean> {
	public Collection<T> filter(Executor exec, Collection<T> c);
	public <C extends Collection<T>> C filter(Executor exec, Collection<T> c, C into);
}
