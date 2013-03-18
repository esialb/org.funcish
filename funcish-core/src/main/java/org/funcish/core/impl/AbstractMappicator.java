package org.funcish.core.impl;

import java.util.Collection;

import org.funcish.core.coll.ArrayCollection;
import org.funcish.core.fn.Mappicator;

public abstract class AbstractMappicator<K, V> extends AbstractMapping<K, V> implements Mappicator<K, V> {

	public AbstractMappicator(Class<K> k, Class<V> v) {
		super(k, v);
	}
	
	protected <C extends Collection<V>> C innerOver(C out, Collection<K> c) {
		int index = 0;
		for(K e : c) {
			try {
				out.add(v().cast(map(k().cast(e), index++)));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return out;
	}

	public Collection<V> over(Collection<K> c) {
		return innerOver(new ArrayCollection<V>(), c);
	}
	
	public <C extends Collection<V>> C overInto(Collection<K> c, C into) {
		return innerOver(into, c);
	}


}
