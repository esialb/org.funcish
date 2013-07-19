package org.funcish.core.impl;

import java.util.ArrayDeque;
import java.util.Deque;

import org.funcish.core.coll.ArrayFunctionalCollection;
import org.funcish.core.fn.IntoIterable;
import org.funcish.core.fn.MultiMapper;
import org.funcish.core.fn.MultiReceiver;

public abstract class AbstractMultiMapper<K, V> extends AbstractMultiMapping<K, V> implements MultiMapper<K, V> {

	private Deque<V> q;
	private MultiReceiver<V> r;
	
	public AbstractMultiMapper(Class<K> k, Class<V> v) {
		super(k, v);
		q = new ArrayDeque<V>();
		r = new AbstractMultiReceiver<V>(v) {
			@Override
			public void receive0(V v) throws Exception {
				q.add(v);
			}
		};
	}

	@Override
	public IntoIterable<V> over(Iterable<? extends K> c) {
		return map(c);
	}

	@Override
	public IntoIterable<V> map(Iterable<? extends K> c) {
		ArrayFunctionalCollection<V> ret = new ArrayFunctionalCollection<V>(v());
		int index = 0;
		for(K e : c) {
			map(e, r, index);
			ret.addAll(q);
			q.clear();
		}
		return ret;
	}

}
