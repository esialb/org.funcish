package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mappicator;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.ParaMappicator;
import org.funcish.core.impl.AbstractMappicator;
import org.funcish.core.impl.ProxyMappicator;
import org.funcish.core.impl.ProxyMapping;
import org.funcish.core.impl.ProxyParaMappicator;

/**
 * Utility class for methods to create or wrap {@link Mapping}s
 * @author robin
 *
 */
public class Mappings {
	/**
	 * Wrap the argument arbitrary {@link Function} as a {@link Mapping}.
	 * @param k
	 * @param target
	 * @return
	 */
	public static <K, V> Mapping<K, V> mapping(Class<K> k, Function<V> target) {
		return new ProxyMapping<K, V>(k, target);
	}
	
	/**
	 * Returns the argument {@link Mapping} as a {@link Mappicator}, either by
	 * casting directly or, failing that, by wrapping
	 * @param target
	 * @return
	 */
	public static <K, V> Mappicator<K, V> mappicator(Mapping<K, V> target) {
		if(target instanceof Mappicator<?, ?>)
			return (Mappicator<K, V>) target;
		return new ProxyMappicator<K, V>(target);
	}
	
	/**
	 * Returns the argument {@link Mapping} as a {@link ParaMappicator}, either
	 * by casting directly or, failing that, by wrapping
	 * @param target
	 * @return
	 */
	public static <K, V> ParaMappicator<K, V> paraMappicator(Mapping<K, V> target) {
		if(target instanceof ParaMappicator<?, ?>)
			return (ParaMappicator<K, V>) target;
		return new ProxyParaMappicator<K, V>(target);
	}
	
	/**
	 * Returns a new {@link Mappicator} that narrows the inputs of the argument {@link Mapping}
	 * @param l
	 * @param mapping
	 * @return
	 */
	public static <K, L extends K, V> Mappicator<L, V> narrow(Class<L> l, final Mapping<K, V> mapping) {
		return new NarrowingMappicator<K, L, V>(l, mapping.v(), mapping);
	}
	
	/**
	 * Returns a new {@link Mappicator} that widens the outputs of the argument {@link Mapping}
	 * @param u
	 * @param mapping
	 * @return
	 */
	public static <K, U, V extends U> Mappicator<K, U> widen(Class<U> u, final Mapping<K, V> mapping) {
		return new WideningMappicator<K, U, V>(mapping.k(), u, mapping);
	}
	
	/**
	 * Returns a new {@link Mappicator} that maps from {@link Class} to either {@link Class#getSimpleName()},
	 * or if that returns an empty string, {@link Class#getName()}.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Mappicator<Class<?>, String> classSimpleName() {
		return new ClassSimpleName((Class) Class.class, String.class);
	}

	/**
	 * Returns a new {@link Mappicator} that casts from K to V
	 * @param k
	 * @param v
	 * @return
	 */
	public static <K, V> Mappicator<K, V> classCast(Class<K> k, Class<V> v) {
		return new ClassCast<K, V>(k, v, v);
	}
	
	/**
	 * Return a new {@link Mappicator} that calls {@link Class#newInstance()}
	 * @param t
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Mappicator<Class<? extends T>, T> classNewInstance(Class<T> t) {
		return new ClassNewInstance<T>((Class) Class.class, t);
	}
	
	/**
	 * Return a new {@link Mappicator} that calls {@link Class#asSubclass(Class)}
	 * @param u
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T, U extends T> Mappicator<Class<? extends T>, Class<? extends U>> classAsSubclass(Class<U> u) {
		return new ClassAsSubclass<T, U>((Class) Class.class, (Class) Class.class, u);
	}
	
	public static <V> Mappicator<String, Class<? extends V>> classForName(Class<V> v) {
		return new ClassForName<V>(String.class, (Class) Class.class, v);
	}
	
	private static class ClassForName<V> extends
			AbstractMappicator<String, Class<? extends V>> {
		private final Class<V> v;

		private ClassForName(Class<String> k, Class<Class<? extends V>> v,
				Class<V> v2) {
			super(k, v);
			this.v = v2;
		}

		@Override
		public Class<? extends V> map0(String key, Integer index)
				throws Exception {
			return Class.forName(key).asSubclass(v);
		}
	}

	private static class ClassAsSubclass<T, U> extends
			AbstractMappicator<Class<? extends T>, Class<? extends U>> {
		private final Class<U> u;

		private ClassAsSubclass(Class<Class<? extends T>> k,
				Class<Class<? extends U>> v, Class<U> u) {
			super(k, v);
			this.u = u;
		}

		@Override
		public Class<? extends U> map0(Class<? extends T> key, Integer index)
				throws Exception {
			return key.asSubclass(u);
		}
	}

	private static class ClassNewInstance<T> extends
			AbstractMappicator<Class<? extends T>, T> {
		private ClassNewInstance(Class<Class<? extends T>> k, Class<T> v) {
			super(k, v);
		}

		@Override
		public T map0(Class<? extends T> key, Integer index) throws Exception {
			return key.newInstance();
		}
	}

	private static class ClassCast<K, V> extends AbstractMappicator<K, V> {
		private final Class<V> v;

		private ClassCast(Class<K> k, Class<V> v, Class<V> v2) {
			super(k, v);
			this.v = v2;
		}

		@Override
		public V map0(K key, Integer index) throws Exception {
			return v.cast(key);
		}
	}

	private static class WideningMappicator<K, U, V extends U> extends AbstractMappicator<K, U> {
		private final Mapping<K, V> mapping;

		private WideningMappicator(Class<K> k, Class<U> v, Mapping<K, V> mapping) {
			super(k, v);
			this.mapping = mapping;
		}

		@Override
		public U map0(K key, Integer index) throws Exception {
			return mapping.map(key, index);
		}
	}

	private static class NarrowingMappicator<K, L extends K, V> extends AbstractMappicator<L, V> {
		private final Mapping<K, V> mapping;

		private NarrowingMappicator(Class<L> k, Class<V> v, Mapping<K, V> mapping) {
			super(k, v);
			this.mapping = mapping;
		}

		@Override
		public V map0(L key, Integer index) throws Exception {
			return mapping.map(key, index);
		}
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
