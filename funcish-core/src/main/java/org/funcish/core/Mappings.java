package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mappicator;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.ParaMappicator;
import org.funcish.core.impl.AbstractMappicator;
import org.funcish.core.impl.AbstractMapping;
import org.funcish.core.impl.ProxyMappicator;
import org.funcish.core.impl.ProxyMapping;
import org.funcish.core.impl.ProxyParaMappicator;

public class Mappings {
	
	public static <K, V> Mapping<K, V> mapping(Class<K> k, Function<V> target) {
		return new ProxyMapping<K, V>(k, target);
	}
	
	public static <K, V> Mappicator<K, V> mappicator(Mapping<K, V> target) {
		return new ProxyMappicator<K, V>(target);
	}
	
	public static <K, V> ParaMappicator<K, V> paraMappicator(Mapping<K, V> target) {
		return new ProxyParaMappicator<K, V>(target);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Mappicator<Class<?>, String> classSimpleName() {
		return new ClassSimpleName((Class) Class.class, String.class);
	}
	
	public static <K, L extends K, V> Mapping<L, V> narrow(Class<L> l, final Mapping<K, V> mapping) {
		return new AbstractMapping<L, V>(l, mapping.v()) {
			@Override
			public V map0(L key, Integer index) throws Exception {
				return mapping.map(key, index);
			}
		};
	}
	
	public static <K, U, V extends U> Mapping<K, U> widen(Class<U> u, final Mapping<K, V> mapping) {
		return new AbstractMapping<K, U>(mapping.k(), u) {
			@Override
			public U map0(K key, Integer index) throws Exception {
				return mapping.map(key, index);
			}
		};
	}
	
	private static class ClassSimpleName extends AbstractMappicator<Class<?>, String> {
		private ClassSimpleName(Class<Class<?>> k, Class<String> v) {
			super(k, v);
		}
	
		@Override
		public String map0(Class<?> key, Integer index) throws Exception {
			String ret = key.getSimpleName();
			if(ret.isEmpty())
				ret = key.getName();
			return ret;
		}
	}

	private Mappings() {}
}
