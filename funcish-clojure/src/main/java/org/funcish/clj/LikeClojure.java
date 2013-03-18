package org.funcish.clj;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducator;

public class LikeClojure {
	
	public static Reducator<Object, Object> reducator(Function<?> fn) {
		return new ClojurelikeProxyReducator(fn);
	}

	private LikeClojure() {}
}
