package org.funcish.core.impl;

import org.funcish.core.fn.MultiReceiver;

public abstract class AbstractMultiReceiver<V> extends AbstractFunction<Void> implements MultiReceiver<V> {

	private Class<V> v;
	
	public abstract void receive0(V v) throws Exception;
	
	public AbstractMultiReceiver(Class<V> v) {
		super(Void.class, new Class<?>[] {v});
		this.v = v;
	}

	@Override
	public Void call(Object... args) throws Exception {
		receive(v.cast(args[0]));
		return null;
	}

	@Override
	public Class<V> v() {
		return v;
	}

	@Override
	public void receive(V v) {
		try {
			receive0(v);
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	

}
