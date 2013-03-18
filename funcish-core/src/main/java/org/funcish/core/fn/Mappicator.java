package org.funcish.core.fn;

import java.util.Collection;

public interface Mappicator<K, V> extends Mapping<K, V>, Applicator<K, Collection<V>, V> {
	public <C extends Collection<V>> C overInto(Collection<K> c, C into);
}
