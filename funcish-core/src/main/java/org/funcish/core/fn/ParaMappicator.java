package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaMappicator<K, V> extends Mappicator<K, V>, ParaApplicator<K, Collection<V>, V> {
	public Collection<V> map(Executor exec, Collection<K> c);
	public <C extends Collection<V>> C map(Executor exec, Collection<K> c, C into);
}
