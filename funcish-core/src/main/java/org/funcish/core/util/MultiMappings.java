package org.funcish.core.util;

import org.funcish.core.fn.MultiMapper;
import org.funcish.core.fn.MultiMapping;
import org.funcish.core.fn.MultiReceiver;
import org.funcish.core.impl.AbstractMultiMapper;
import org.funcish.core.impl.ProxyMultiMapper;

public class MultiMappings {
	public static <K, V> MultiMapper<K, V> mapper(MultiMapping<K, V> m) {
		return new ProxyMultiMapper<K, V>(m);
	}
	
	public static <K, L extends K, V> MultiMapper<L, V> narrow(Class<L> l, final MultiMapping<K, V> m) {
		return new NarrowingMultiMapper<K, L, V>(l, m.v(), m);
	}
	
	public static <K, U, V extends U> MultiMapper<K, U> widen(Class<U> u, final MultiMapping<K, V> m) {
		return new WideningMultiMapper<K, U, V>(m.k(), u, m);
	}
	
	private static class WideningMultiMapper<K, U, V extends U> extends
			AbstractMultiMapper<K, U> {
		private final MultiMapping<K, V> m;

		private WideningMultiMapper(Class<K> k, Class<U> v, MultiMapping<K, V> m) {
			super(k, v);
			this.m = m;
		}

		@Override
		public void map0(K key, MultiReceiver<? super U> receiver, Integer index)
				throws Exception {
			m.map(key, receiver, index);
		}
	}

	private static class NarrowingMultiMapper<K, L extends K, V> 
	extends AbstractMultiMapper<L, V> {
		private final MultiMapping<K, V> m;
	
		private NarrowingMultiMapper(Class<L> k, Class<V> v,
				MultiMapping<K, V> m) {
			super(k, v);
			this.m = m;
		}
	
		@Override
		public void map0(L key, MultiReceiver<? super V> receiver, Integer index)
				throws Exception {
			m.map(key, receiver, index);
		}
	}

	private MultiMappings() {}
}
