package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.ParaPredicator;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Predicator;
import org.funcish.core.impl.AbstractPredicate;
import org.funcish.core.impl.ProxyParaPredicator;
import org.funcish.core.impl.ProxyPredicate;
import org.funcish.core.impl.ProxyPredicator;

public class Predicates {
	
	public static <T> Predicate<T> predicate(Class<T> t, Function<Boolean> target) {
		return new ProxyPredicate<T>(t, target);
	}
	
	public static <T> Predicator<T> predicator(Predicate<T> target) {
		return new ProxyPredicator<T>(target);
	}
	
	public static <T> ParaPredicator<T> paraPredicator(Predicate<T> target) {
		return new ProxyParaPredicator<T>(target);
	}
	
	public static <T, U extends T> Predicate<U> narrow(Class<U> u, final Predicate<T> test) {
		return new NarrowingPredicate<T, U>(u, test);
	}
	
	private static class NarrowingPredicate<T, U extends T> extends AbstractPredicate<U> {
		private final Predicate<T> test;
	
		private NarrowingPredicate(Class<U> t, Predicate<T> test) {
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
