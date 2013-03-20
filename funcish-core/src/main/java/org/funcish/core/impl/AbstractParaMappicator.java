package org.funcish.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.funcish.core.fn.ParaMappicator;
import org.funcish.core.util.ArrayCollection;

public abstract class AbstractParaMappicator<K, V> extends AbstractMappicator<K, V> implements ParaMappicator<K, V> {

	public AbstractParaMappicator(Class<K> k, Class<V> v) {
		super(k, v);
	}

	protected <C extends Collection<V>> C paraInnerOver(C out, Executor exec, Collection<? extends K> c) {
		Collection<Future<V>> futures = new ArrayList<Future<V>>();
		int index = 0;
		for(K e : c) {
			final K fe = e;
			final int findex = index++;
			RunnableFuture<V> f = new FutureTask<V>(new Callable<V>() {
				public V call() throws Exception {
					return v().cast(map0(k().cast(fe), findex));
				}
			});
			exec.execute(f);
			futures.add(f);
		}
		try {
			for(Future<V> f : futures) {
				out.add(f.get());
			}
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return out;
	}
	
	public Collection<V> over(Executor exec, Collection<? extends K> c) {
		return paraInnerOver(new ArrayCollection<V>(), exec, c);
	}

	public <C extends Collection<V>> C into(Executor exec, Collection<? extends K> c, C into) {
		return paraInnerOver(into, exec, c);
	}

	@Override
	public Collection<V> map(Executor exec, Collection<? extends K> c) {
		return over(exec, c);
	}
	
	@Override
	public <C extends Collection<V>> C map(Executor exec, Collection<? extends K> c, C into) {
		return into(exec, c, into);
	}
}
