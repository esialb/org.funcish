/**
 * org.funcish: functional utilities for Java
 * 
 * Copyright 2013 Robin Kirkman.
 * 
 * Released under a BSD license.
 * 
 * Copyright (c) 2013, Robin Kirkman
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *  o  Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *  o  Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *  o  Neither the name of org.funcish nor the names of its contributors may be used to endorse 
 *     or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.funcish.core.util;

import java.util.Map;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mapper;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.ParaMapper;
import org.funcish.core.impl.AbstractMapper;
import org.funcish.core.impl.ProxyMapper;
import org.funcish.core.impl.ProxyMapping;
import org.funcish.core.impl.ProxyParaMapper;

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
	 * Returns the argument {@link Mapping} as a {@link Mapper}, either by
	 * casting directly or, failing that, by wrapping
	 * @param target
	 * @return
	 */
	public static <K, V> Mapper<K, V> mapper(Mapping<K, V> target) {
		if(target instanceof Mapper<?, ?>)
			return (Mapper<K, V>) target;
		return new ProxyMapper<K, V>(target);
	}
	
	/**
	 * Returns the argument {@link Mapping} as a {@link ParaMapper}, either
	 * by casting directly or, failing that, by wrapping
	 * @param target
	 * @return
	 */
	public static <K, V> ParaMapper<K, V> paraMapper(Mapping<K, V> target) {
		if(target instanceof ParaMapper<?, ?>)
			return (ParaMapper<K, V>) target;
		return new ProxyParaMapper<K, V>(target);
	}
	
	/**
	 * Returns a new {@link Mapper} that narrows the inputs of the argument {@link Mapping}
	 * @param l
	 * @param mapping
	 * @return
	 */
	public static <K, L extends K, V> Mapper<L, V> narrow(Class<L> l, final Mapping<K, V> mapping) {
		return new NarrowingMappicator<K, L, V>(l, mapping.v(), mapping);
	}
	
	/**
	 * Returns a new {@link Mapper} that widens the outputs of the argument {@link Mapping}
	 * @param u
	 * @param mapping
	 * @return
	 */
	public static <K, U, V extends U> Mapper<K, U> widen(Class<U> u, final Mapping<K, V> mapping) {
		return new WideningMappicator<K, U, V>(mapping.k(), u, mapping);
	}
	
	/**
	 * Returns a new {@link Mapper} that maps from {@link Class} to either {@link Class#getSimpleName()},
	 * or if that returns an empty string, {@link Class#getName()}.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Mapper<Class<?>, String> classSimpleName() {
		return new ClassSimpleName((Class) Class.class, String.class);
	}

	/**
	 * Returns a new {@link Mapper} that casts from K to V
	 * @param k
	 * @param v
	 * @return
	 */
	public static <K, V> Mapper<K, V> classCast(Class<K> k, Class<V> v) {
		return new ClassCast<K, V>(k, v, v);
	}
	
	/**
	 * Return a new {@link Mapper} that calls {@link Class#newInstance()}
	 * @param t
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Mapper<Class<? extends T>, T> classNewInstance(Class<T> t) {
		return new ClassNewInstance<T>((Class) Class.class, t);
	}
	
	/**
	 * Return a new {@link Mapper} that calls {@link Class#asSubclass(Class)}
	 * @param u
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T, U extends T> Mapper<Class<? extends T>, Class<? extends U>> classAsSubclass(Class<U> u) {
		return new ClassAsSubclass<T, U>((Class) Class.class, (Class) Class.class, u);
	}
	
	public static <V> Mapper<String, Class<? extends V>> classForName(Class<V> v) {
		return new ClassForName<V>(String.class, (Class) Class.class, v);
	}
	
	public static <K, V> Mapper<K, V> repeat(Class<K> k, Class<V> v, V val) {
		return new RepeatMapper<K, V>(k, v, val);
	}
	
	public static <K, V> Mapper<K, V> fromMap(Class<K> k, Class<V> v, Map<K, V> map) {
		return new MapMapper<K, V>(k, v, map);
	}
	
	private static class MapMapper<K, V> extends AbstractMapper<K, V> {
		private final Map<K, V> map;

		private MapMapper(Class<K> k, Class<V> v, Map<K, V> map) {
			super(k, v);
			this.map = map;
		}

		@Override
		public V map0(K key, Integer index) throws Exception {
			return map.get(key);
		}
	}

	private static class RepeatMapper<K, V> extends AbstractMapper<K, V> {
		private final V val;

		private RepeatMapper(Class<K> k, Class<V> v, V val) {
			super(k, v);
			this.val = val;
		}

		@Override
		public V map0(K key, Integer index) throws Exception {
			return val;
		}
	}

	private static class ClassForName<V> extends
			AbstractMapper<String, Class<? extends V>> {
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
			AbstractMapper<Class<? extends T>, Class<? extends U>> {
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
			AbstractMapper<Class<? extends T>, T> {
		private ClassNewInstance(Class<Class<? extends T>> k, Class<T> v) {
			super(k, v);
		}

		@Override
		public T map0(Class<? extends T> key, Integer index) throws Exception {
			return key.newInstance();
		}
	}

	private static class ClassCast<K, V> extends AbstractMapper<K, V> {
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

	private static class WideningMappicator<K, U, V extends U> extends AbstractMapper<K, U> {
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

	private static class NarrowingMappicator<K, L extends K, V> extends AbstractMapper<L, V> {
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

	private static class ClassSimpleName extends AbstractMapper<Class<?>, String> {
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
