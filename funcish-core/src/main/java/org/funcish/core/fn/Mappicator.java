package org.funcish.core.fn;

import java.util.Collection;

public interface Mappicator<K, V> extends Mapping<K, V>, Applicator<K, Collection<V>, V> {
	public Collection<V> map(Collection<K> c);
	public <C extends Collection<V>> C map(Collection<K> c, C into);
}
