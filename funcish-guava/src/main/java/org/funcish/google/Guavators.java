package org.funcish.google;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Predicate;
import org.funcish.core.impl.AbstractFunction;
import org.funcish.core.impl.AbstractPredicator;
import org.funcish.core.impl.ProxyFunction;
import org.funcish.core.impl.ProxyPredicator;

public class Guavators {
	public static <F, T> DualFunction<F, T> dualFunction(Class<T> t, Class<F> f, com.google.common.base.Function<F, T> gf) {
		return new DualGFunction<F, T>(t, new Class<?>[]{f}, f, gf, t);
	}
	
	public static <F, T> DualFunction<F, T> dualFunction(com.google.common.base.Function<F, T> gf) {
		return new DualUncheckedGFunction<F, T>(gf);
	}
	
	public static <F, T> DualFunction<F, T> dualFunction(Class<F> f, Function<T> fn) {
		return new DualProxyFunction<F, T>(fn, f);
	}
	
	public static <F, T> DualFunction<F, T> dualFunction(Function<T> fn) {
		return new DualUncheckedProxyFunction<F, T>(fn);
	}
	
	public static <T> DualPredicate<T> dualPredicate(Class<T> t, com.google.common.base.Predicate<T> gp) {
		return new DualGPredicate<T>(t, gp);
	}
	
	public static <T> DualPredicate<T> dualPredicate(com.google.common.base.Predicate<T> gp) {
		return new DualUncheckedGPredicate<T>(gp);
	}
	
	public static <T> DualPredicate<T> dualPredicate(Predicate<T> fp) {
		return new DualProxyPredicate<T>(fp);
	}

	private static class DualProxyPredicate<T> extends ProxyPredicator<T> implements DualPredicate<T> {
		private DualProxyPredicate(Predicate<T> target) {
			super(target);
		}

		@Override
		public boolean apply(T input) {
			return test(input, null);
		}
	}


	private static class DualUncheckedGPredicate<T> extends AbstractPredicator<T> implements DualPredicate<T> {
		private final com.google.common.base.Predicate<T> gp;

		private DualUncheckedGPredicate(com.google.common.base.Predicate<T> gp) {
			super((Class) Object.class);
			this.gp = gp;
		}

		@Override
		public boolean test0(T value, Integer index) throws Exception {
			return gp.apply(value);
		}

		@Override
		public boolean apply(T input) {
			return gp.apply(input);
		}
	}

	
	private static class DualGPredicate<T> extends AbstractPredicator<T> implements DualPredicate<T> {
		private final com.google.common.base.Predicate<T> gp;

		private DualGPredicate(Class<T> t, com.google.common.base.Predicate<T> gp) {
			super(t);
			this.gp = gp;
		}

		@Override
		public boolean test0(T value, Integer index) throws Exception {
			return gp.apply(t().cast(value));
		}

		@Override
		public boolean apply(T input) {
			return gp.apply(t().cast(input));
		}
	}

	private static class DualUncheckedProxyFunction<F, T> extends ProxyFunction<T> implements DualFunction<F, T> {
		public DualUncheckedProxyFunction(Function<T> target) {
			super(target);
		}

		@Override
		public T apply(F input) {
			try {
				return ret().cast(proxiedTarget().call(input));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
	private static class DualProxyFunction<F, T> extends ProxyFunction<T> implements DualFunction<F, T> {
		private final Class<F> f;

		private DualProxyFunction(Function<T> target, Class<F> f) {
			super(target);
			this.f = f;
		}

		public T apply(F input) {
			try {
				return ret().cast(proxiedTarget().call(f.cast(input)));
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	private static class DualUncheckedGFunction<F, T> extends AbstractFunction<T> implements DualFunction<F, T> {
		private final com.google.common.base.Function<F, T> gf;

		private DualUncheckedGFunction(com.google.common.base.Function<F, T> gf) {
			super((Class) Object.class, new Class<?>[] {Object.class});
			this.gf = gf;
		}

		@Override
		public T call(Object... args) throws Exception {
			return gf.apply((F) args[0]);
		}

		@Override
		public T apply(F input) {
			return gf.apply(input);
		}
	}

	private static class DualGFunction<F, T> extends AbstractFunction<T> implements DualFunction<F, T> {
		private final Class<F> f;
		private final com.google.common.base.Function<F, T> gf;
		private final Class<T> t;
	
		private DualGFunction(Class<T> ret, Class<?>[] fnargs, Class<F> f,
				com.google.common.base.Function<F, T> gf, Class<T> t) {
			super(ret, fnargs);
			this.f = f;
			this.gf = gf;
			this.t = t;
		}
	
		@Override
		public T call(Object... args) throws Exception {
			return t.cast(gf.apply(f.cast(args[0])));
		}

		@Override
		public T apply(F input) {
			return t.cast(gf.apply(f.cast(input)));
		}
	}

	private Guavators() {}
}
