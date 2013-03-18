package org.funcish.core.fn;

import java.util.Collection;
import java.util.concurrent.Executor;

public interface ParaMappicator<K, V> extends Mappicator<K, V>, ParaApplicator<K, Collection<V>, V> {
	public <C extends Collection<V>> C into(Executor exec, Collection<K> c, C into);
	
	public Collection<V> map(Executor exec, Collection<K> c);
}
