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

package org.funcish.google;

import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.impl.AbstractMapper;
import org.funcish.core.impl.AbstractPredicator;
import org.funcish.core.impl.ProxyMapper;
import org.funcish.core.impl.ProxyPredicator;

public class DualFunctions {
	public static <F, T> DualMapping<F, T> dualMapping(Class<T> t, Class<F> f, com.google.common.base.Function<F, T> gf) {
		return new DualGMapping<F, T>(t, f, gf);
	}
	
	public static <F, T> DualMapping<F, T> dualMapping(com.google.common.base.Function<F, T> gf) {
		return new DualUncheckedGMapping<F, T>(gf);
	}
	
	public static <F, T> DualMapping<F, T> dualMapping(Mapping<F, T> fn) {
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

	private static class DualProxyMapping<F, T> extends ProxyMapper<F, T> implements DualMapping<F, T> {
		private DualProxyMapping(Mapping<F, T> target) {
			super(target);
		}

		public T apply(F input) {
			return proxiedTarget().map(input, null);
		}
	}

	private static class DualUncheckedGMapping<F, T> extends AbstractMapper<F, T> implements DualMapping<F, T> {
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

	private static class DualGMapping<F, T> extends AbstractMapper<F, T> implements DualMapping<F, T> {
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

	private DualFunctions() {}
}
