package org.funcish.core;

import org.funcish.core.fn.Function;
import org.funcish.core.fn.Mappicator;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.ParaMappicator;
import org.funcish.core.impl.ProxyMappicator;
import org.funcish.core.impl.ProxyMapping;
import org.funcish.core.impl.ProxyParaMappicator;

public class Mappings {
	
	public static <K, V> Mapping<K, V> mapping(Class<K> k, Function<V> target) {
		return new ProxyMapping<K, V>(k, target);
	}
	
	public static <K, V> Mappicator<K, V> mappicator(Mapping<K, V> target) {
		return new ProxyMappicator<K, V>(target);
	}
	
	public static <K, V> ParaMappicator<K, V> paraMappicator(Mapping<K, V> target) {
		return new ProxyParaMappicator<K, V>(target);
	}
	
	private Mappings() {}
}
