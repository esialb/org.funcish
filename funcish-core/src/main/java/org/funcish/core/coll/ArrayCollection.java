package org.funcish.core.coll;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ArrayCollection<E> extends AbstractCollection<E> {
	private Collection<E> backing = new ArrayList<E>();

	public ArrayCollection() {
	}
	
	public ArrayCollection(Collection<? extends E> c) {
		backing.addAll(c);
	}
	
	@Override
	public Iterator<E> iterator() {
		return backing.iterator();
	}

	@Override
	public int size() {
		return backing.size();
	}

	@Override
	public boolean contains(Object o) {
		return backing.contains(o);
	}

	@Override
	public boolean add(E e) {
		return backing.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return backing.remove(o);
	}
	
}
