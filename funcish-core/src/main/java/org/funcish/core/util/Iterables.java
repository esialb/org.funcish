package org.funcish.core.util;

import java.util.Collection;

public class Iterables {
	
	public static <V, C extends Collection<? super V>> C into(Iterable<V> itr, C into) {
		for(V v : itr) {
			into.add(v);
		}
		return into;
	}
	
	private Iterables() {}
}
