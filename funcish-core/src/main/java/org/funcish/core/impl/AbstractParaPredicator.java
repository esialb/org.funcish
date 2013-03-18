package org.funcish.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.funcish.core.coll.ArrayCollection;
import org.funcish.core.fn.ParaPredicator;

public abstract class AbstractParaPredicator<T> extends AbstractPredicator<T> implements ParaPredicator<T> {

	public AbstractParaPredicator(Class<T> t) {
		super(t);
	}

	protected <C extends Collection<T>> C paraInnerOver(C out, Executor exec, Collection<T> c) {
		Collection<Future<Object[]>> futures = new ArrayList<Future<Object[]>>();
		int index = 0;
		for(T e : c) {
			final T fe = e;
			final int findex = index++;
			RunnableFuture<Object[]> f = new FutureTask<Object[]>(new Callable<Object[]>() {
				public Object[] call() throws Exception {
					return new Object[] {test0(t().cast(fe), findex), fe};
				}
			});
			exec.execute(f);
			futures.add(f);
		}
		try {
			for(Future<Object[]> f : futures) {
				if(((Boolean) f.get()[0]))
					out.add(t().cast(f.get()[1]));
			}
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return out;
	}

	public Collection<T> over(Executor exec, Collection<T> c) {
		return paraInnerOver(new ArrayCollection<T>(), exec, c);
	}

	public <C extends Collection<T>> C into(Executor exec, Collection<T> c, C into) {
		return paraInnerOver(into, exec, c);
	}

	@Override
	public Collection<T> filter(Executor exec, Collection<T> c) {
		return over(exec, c);
	}
	
	@Override
	public <C extends Collection<T>> C filter(Executor exec, Collection<T> c, C into) {
		return into(exec, c, into);
	}
}
