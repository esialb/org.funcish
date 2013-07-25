package org.funcish.core.fn;

public interface MultiReceiver<V> extends Function<Void> {
	public Class<V> v();
	
	public void receive(V v);
}
