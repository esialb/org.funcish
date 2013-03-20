package org.funcish.core.fn;

import java.util.Collection;

/**
 * {@link Applicator} that applies a {@link Mapping} to a {@link Collection}
 * @author robin
 *
 * @param <K>
 * @param <V>
 */
public interface Mappicator<K, V> extends Mapping<K, V>, Applicator<K, Collection<V>, V> {
	public Collection<V> map(Collection<? extends K> c);
	public <C extends Collection<? super V>> C map(Collection<? extends K> c, C into);
}
