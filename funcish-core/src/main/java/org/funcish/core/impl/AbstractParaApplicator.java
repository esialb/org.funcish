package org.funcish.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.funcish.core.fn.ParaApplicator;

public abstract class AbstractParaApplicator<E, T> extends AbstractApplicator<E, T> implements ParaApplicator<E, T, T> {

	public AbstractParaApplicator(Class<T> ret, Class<?>[] fnargs) {
		super(ret, fnargs);
	}

	public T over(Executor exec, Collection<E> c) {
		Collection<Future<T>> futures = new ArrayList<Future<T>>();
		T ret = null;
		int index = 0;
		for(E e : c) {
			final E fe = e;
			final int findex = index++;
			RunnableFuture<T> f = new FutureTask<T>(new Callable<T>() {
				public T call() throws Exception {
					return AbstractParaApplicator.this.call(args(fe, findex));
				}
			});
			exec.execute(f);
			futures.add(f);
		}
		try {
			for(Future<T> f : futures) {
				ret = f.get();
			}
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return ret;
	}

}
