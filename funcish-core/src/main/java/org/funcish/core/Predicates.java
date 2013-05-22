package org.funcish.core;

import java.util.regex.Pattern;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.ParaPredicator;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Predicator;
import org.funcish.core.impl.AbstractPredicator;
import org.funcish.core.impl.ProxyParaPredicator;
import org.funcish.core.impl.ProxyPredicate;
import org.funcish.core.impl.ProxyPredicator;

/**
 * Utility methods for creating and wrapping {@link Predicate}s
 * @author robin
 *
 */
public class Predicates {
	/**
	 * Return the argument {@link Function} as a {@link Predicate}
	 * @param t
	 * @param target
	 * @return
	 */
	public static <T> Predicate<T> predicate(Class<T> t, Function<Boolean> target) {
		return new ProxyPredicate<T>(t, target);
	}
	
	/**
	 * Return a {@link Predicator} from the argument {@link Predicate}, either by
	 * casting, or failing that, wrapping
	 * @param target
	 * @return
	 */
	public static <T> Predicator<T> predicator(Predicate<T> target) {
		if(target instanceof Predicator<?>)
			return (Predicator<T>) target;
		return new ProxyPredicator<T>(target);
	}
	
	/**
	 * Return a {@link ParaPredicator} from the argument {@link Predicate}, either by
	 * casting, or failing that, wrapping
	 * @param target
	 * @return
	 */
	public static <T> ParaPredicator<T> paraPredicator(Predicate<T> target) {
		if(target instanceof ParaPredicator<?>)
			return (ParaPredicator<T>) target;
		return new ProxyParaPredicator<T>(target);
	}
	
	/**
	 * Returns a new {@link Predicator} that narrows the inputs of the argument
	 * @param u
	 * @param test
	 * @return
	 */
	public static <T, U extends T> Predicator<U> narrow(Class<U> u, Predicate<T> test) {
		return new NarrowingPredicator<T, U>(u, test);
	}
	
	/**
	 * Returns a new {@link Predicate} that lazily evaluates the argument {@link Predicate}s
	 * in order, returning false if any return false, otherwise returning true.
	 * @param first
	 * @param rest
	 * @return
	 */
	public static <T> Predicator<T> and(Predicate<T> first, Predicate<T>... rest) {
		return new AndPredicator<T>(first.t(), rest, first);
	}
	
	/**
	 * Returns a new {@link Predicate} that lazily evaluates the argument {@link Predicate}s
	 * in order, returning true if any return true, otherwise returning false.
	 * @param first
	 * @param rest
	 * @return
	 */
	public static <T> Predicator<T> or(Predicate<T> first, Predicate<T>... rest) {
		return new OrPredicator<T>(first.t(), first, rest);
	}
	
	/**
	 * Return a new {@link Predicate} that returns the opposite of the argument
	 * @param p
	 * @return
	 */
	public static <T> Predicator<T> not(Predicate<T> p) {
		return new NotPredicator<T>(p.t(), p);
	}
	
	/**
	 * Returns a new {@link Predicate} that calls {@link Class#isInstance(Object)}
	 * @param t
	 * @return
	 */
	public static <T> Predicator<Object> classIsInstance(Class<T> t) {
		return new ClassIsInstance<T>(Object.class, t);
	}
	
	public static Predicator<CharSequence> patternFind(Pattern p) {
		return new PatternFind(CharSequence.class, p);
	}
	
	/**
	 * Returns a new {@link Predicate} that calls {@link Class#isAssignableFrom(Class)}
	 * @param cls
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Predicator<Class<?>> classIsAssignableFrom(Class<?> cls) {
		return new ClassIsAssignableFrom((Class)Class.class, cls);
	}
	
	public static <T> Predicator<T> repeat(Class<T> t, final boolean val) {
		return new RepeatPredicator<T>(t, val);
	}
	
	private static class RepeatPredicator<T> extends AbstractPredicator<T> {
		private final boolean val;

		private RepeatPredicator(Class<T> t, boolean val) {
			super(t);
			this.val = val;
		}

		@Override
		public boolean test0(T value, Integer index) throws Exception {
			return val;
		}
	}

	private static class PatternFind extends AbstractPredicator<CharSequence> {
		private final Pattern p;

		private PatternFind(Class<CharSequence> t, Pattern p) {
			super(t);
			this.p = p;
		}

		@Override
		public boolean test0(CharSequence value, Integer index)
				throws Exception {
			return p.matcher(value).find();
		}
	}

	private static class ClassIsAssignableFrom extends
			AbstractPredicator<Class<?>> {
		private final Class<?> cls;

		private ClassIsAssignableFrom(Class<Class<?>> t, Class<?> cls) {
			super(t);
			this.cls = cls;
		}

		@Override
		public boolean test0(Class<?> value, Integer index)
				throws Exception {
			return cls.isAssignableFrom(value);
		}
	}

	private static class ClassIsInstance<T> extends AbstractPredicator<Object> {
		private final Class<T> t;

		private ClassIsInstance(Class<Object> t, Class<T> t2) {
			super(t);
			this.t = t2;
		}

		@Override
		public boolean test0(Object value, Integer index) throws Exception {
			return t.isInstance(value);
		}
	}

	private static class NotPredicator<T> extends AbstractPredicator<T> {
		private Predicate<T> p;

		private NotPredicator(Class<T> t, Predicate<T> p) {
			super(t);
			this.p = p;
		}

		@Override
		public boolean test0(T value, Integer index) throws Exception {
			return !p.test(value, index);
		}
	}

	private static class AndPredicator<T> extends AbstractPredicator<T> {
		private Predicate<T>[] rest;
		private Predicate<T> first;

		private AndPredicator(Class<T> t, Predicate<T>[] rest,
				Predicate<T> first) {
			super(t);
			this.rest = rest;
			this.first = first;
		}

		@Override
		public boolean test0(T value, Integer index) throws Exception {
			if(!first.test(value, index))
				return false;
			for(Predicate<T> p : rest) {
				if(!p.test(value, index))
					return false;
			}
			return true;
		}
	}

	private static class OrPredicator<T> extends AbstractPredicator<T> {
		private Predicate<T> first;
		private Predicate<T>[] rest;

		private OrPredicator(Class<T> t, Predicate<T> first, Predicate<T>[] rest) {
			super(t);
			this.first = first;
			this.rest = rest;
		}

		@Override
		public boolean test0(T value, Integer index) throws Exception {
			if(first.test(value, index))
				return true;
			for(Predicate<T> p : rest) {
				if(p.test(value, index))
					return true;
			}
			return false;
		}
	}

	private static class NarrowingPredicator<T, U extends T> extends AbstractPredicator<U> {
		private Predicate<T> test;
	
		private NarrowingPredicator(Class<U> t, Predicate<T> test) {
			super(t);
			this.test = test;
		}
	
		@Override
		public boolean test0(U value, Integer index) throws Exception {
			return test.test(value, index);
		}
	}

	private Predicates() {}
}
