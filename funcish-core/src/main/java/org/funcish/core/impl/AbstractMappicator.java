package org.funcish.core.impl;

import java.util.Collection;

import org.funcish.core.fn.Mappicator;
import org.funcish.core.util.ArrayCollection;

public abstract class AbstractMappicator<K, V> extends AbstractMapping<K, V> implements Mappicator<K, V> {

	public AbstractMappicator(Class<K> k, Class<V> v) {
		super(k, v);
	}
	
	protected <C extends Collection<V>> C innerOver(C out, Collection<? extends K> c) {
		int index = 0;
		for(K e : c) {
			try {
				out.add(v().cast(map0(k().cast(e), index++)));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return out;
	}

	@Override
	public Collection<V> over(Collection<? extends K> c) {
		return innerOver(new ArrayCollection<V>(), c);
	}
	
	public <C extends Collection<V>> C into(Collection<? extends K> c, C into) {
		return innerOver(into, c);
	}

	@Override
	public Collection<V> map(Collection<? extends K> c) {
		return over(c);
	}

	@Override
	public <C extends Collection<V>> C map(Collection<? extends K> c, C into) {
		return into(c, into);
	}
}
