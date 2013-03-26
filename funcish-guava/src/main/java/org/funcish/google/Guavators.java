package org.funcish.google;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.impl.AbstractFunction;
import org.funcish.core.impl.AbstractMappicator;
import org.funcish.core.impl.AbstractPredicator;
import org.funcish.core.impl.ProxyFunction;
import org.funcish.core.impl.ProxyMappicator;
import org.funcish.core.impl.ProxyPredicator;

public class Guavators {
	public static <F, T> DualMapping<F, T> dualFunction(Class<T> t, Class<F> f, com.google.common.base.Function<F, T> gf) {
		return new DualGMapping<F, T>(t, f, gf);
	}
	
	public static <F, T> DualMapping<F, T> dualFunction(com.google.common.base.Function<F, T> gf) {
		return new DualUncheckedGMapping<F, T>(gf);
	}
	
	public static <F, T> DualMapping<F, T> dualFunction(Mapping<F, T> fn) {
		return new DualProxyMapping<F, T>(fn);
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

	private static class DualProxyMapping<F, T> extends ProxyMappicator<F, T> implements DualMapping<F, T> {
		private DualProxyMapping(Mapping<F, T> target) {
			super(target);
		}

		public T apply(F input) {
			return proxiedTarget().map(input, null);
		}
	}

	private static class DualUncheckedGMapping<F, T> extends AbstractMappicator<F, T> implements DualMapping<F, T> {
		private final com.google.common.base.Function<F, T> gf;

		private DualUncheckedGMapping(com.google.common.base.Function<F, T> gf) {
			super((Class) Object.class, (Class) Object.class);
			this.gf = gf;
		}

		@Override
		public T apply(F input) {
			return gf.apply(input);
		}

		@Override
		public T map0(F key, Integer index) throws Exception {
			return gf.apply(key);
		}
	}

	private static class DualGMapping<F, T> extends AbstractMappicator<F, T> implements DualMapping<F, T> {
		private final com.google.common.base.Function<F, T> gf;
	
		private DualGMapping(Class<T> t, Class<F> f, com.google.common.base.Function<F, T> gf) {
			super(f, t);
			this.gf = gf;
		}
	
		@Override
		public T apply(F input) {
			return gf.apply(input);
		}

		@Override
		public T map0(F key, Integer index) throws Exception {
			return gf.apply(key);
		}
	}

	private Guavators() {}
}
