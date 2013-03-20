package org.funcish.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.funcish.core.fn.ParaPredicator;
import org.funcish.core.util.ArrayCollection;

public abstract class AbstractParaPredicator<T> extends AbstractPredicator<T> implements ParaPredicator<T> {

	public AbstractParaPredicator(Class<T> t) {
		super(t);
	}

	@SuppressWarnings("unchecked")
	protected <U extends T, C extends Collection<? super U>> C paraInnerOver(C out, Executor exec, Collection<? extends U> c) {
		Collection<Future<Object[]>> futures = new ArrayList<Future<Object[]>>();
		int index = 0;
		for(U e : c) {
			final U fe = e;
			final int findex = index++;
			RunnableFuture<Object[]> f = new FutureTask<Object[]>(new Callable<Object[]>() {
				@Override
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
					out.add((U) f.get()[1]);
			}
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return out;
	}

	@Override
	public Collection<T> over(Executor exec, Collection<? extends T> c) {
		return paraInnerOver(new ArrayCollection<T>(), exec, c);
	}

	public <U extends T, C extends Collection<? super U>> C into(Executor exec, Collection<? extends U> c, C into) {
		return paraInnerOver(into, exec, c);
	}

	@Override
	public Collection<T> filter(Executor exec, Collection<? extends T> c) {
		return over(exec, c);
	}
	
	@Override
	public <U extends T, C extends Collection<? super U>> C filter(Executor exec, Collection<? extends U> c, C into) {
		return into(exec, c, into);
	}
}
