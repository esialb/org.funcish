package org.funcish.core.util;

public class Strings {
	public static String join(String delim, Object... vals) {
		StringBuilder sb = new StringBuilder();
		String sep = "";
		for(Object v : vals) {
			sb.append(sep);
			sb.append(v);
			sep = delim;
		}
		return sb.toString();
	}
	
	public static String join(String delim, Iterable<?> vals) {
		StringBuilder sb = new StringBuilder();
		String sep = "";
		for(Object v : vals) {
			sb.append(sep);
			sb.append(v);
			sep = delim;
		}
		return sb.toString();
	}
}
