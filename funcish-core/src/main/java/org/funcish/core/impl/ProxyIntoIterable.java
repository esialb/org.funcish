package org.funcish.core.impl;

import java.util.Collection;
import java.util.Iterator;

import org.funcish.core.fn.IntoIterable;

public class ProxyIntoIterable<E> implements IntoIterable<E>, Proxied<Iterable<E>> {
	protected Iterable<E> itr;
	
	public ProxyIntoIterable(Iterable<E> itr) {
		this.itr = itr;
	}

	@Override
	public Iterator<E> iterator() {
		return itr.iterator();
	}

	@Override
	public <C extends Collection<? super E>> C into(C dest) {
		for(E e : itr) {
			dest.add(e);
		}
		return dest;
	}

	@Override
	public Iterable<E> proxiedTarget() {
		return itr;
	}
}
