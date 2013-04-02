package org.funcish.clj;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Reducer;

public class LikeClojure {
	
	public static Reducer<Object, Object> reducator(Function<?> fn) {
		return new ClojurelikeProxyReducator(fn);
	}

	private LikeClojure() {}
}
