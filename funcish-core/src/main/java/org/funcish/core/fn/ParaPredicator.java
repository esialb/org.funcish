package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaPredicator<T> extends Predicator<T>, ParaApplicator<T, Collection<T>, Boolean> {
	public <C extends Collection<T>> C into(Executor exec, Collection<T> c, C into);

}
