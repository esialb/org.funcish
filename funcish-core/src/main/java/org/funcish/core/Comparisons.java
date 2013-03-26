package org.funcish.core;

import java.util.Comparator;
import java.util.List;

public class Comparisons {
	
	public static <T> Comparator<T> indexOf(final List<? super T> ordered) {
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				Integer p1 = ordered.indexOf(o1);
				Integer p2 = ordered.indexOf(o2);
				return p1.compareTo(p2);
			}
		};
	}
	
	private Comparisons() {}
}
