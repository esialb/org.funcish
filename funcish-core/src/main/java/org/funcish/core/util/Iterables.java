package org.funcish.core.util;

import java.util.Collection;

import org.funcish.core.fn.IntoIterable;
import org.funcish.core.impl.ProxyIntoIterable;

public class Iterables {
	
	public static <E, C extends Collection<? super E>> C into(Iterable<E> itr, C into) {
		for(E v : itr) {
			into.add(v);
		}
		return into;
	}
	
	public static <E> IntoIterable<E> into(Iterable<E> itr) {
		return new ProxyIntoIterable<E>(itr);
	}
	
	
	private Iterables() {}
}
