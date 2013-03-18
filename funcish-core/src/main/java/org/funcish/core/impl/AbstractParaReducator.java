package org.funcish.core.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.funcish.core.fn.ParaReducator;
import org.funcish.core.fn.Reducer;

public abstract class AbstractParaReducator<E, M> extends AbstractReducator<E, M> implements ParaReducator<E, M> {

	private Reducer<M, M> collator;
	
	public AbstractParaReducator(Class<E> e, Class<M> m, M memoStart, Reducer<M, M> collator) {
		super(e, m, memoStart);
		this.collator = collator;
	}

	protected M paraInnerOver(M memo, Executor exec, Collection<E> c) {
		Collection<Future<M>> futures = new ArrayList<Future<M>>();
		int index = 0;
		for(E e : c) {
			final M fmemo = memo;
			final E fe = e;
			final int findex = index++;
			RunnableFuture<M> f = new FutureTask<M>(new Callable<M>() {
				public M call() throws Exception {
					return reduce(fmemo, fe, findex);
				}
			});
			exec.execute(f);
			futures.add(f);
		}
		try {
			index = 0;
			memo = collator().memoStart();
			for(Future<M> f : futures) {
				memo = collator().reduce(memo, f.get(), index++);
			}
		} catch(RuntimeException re) {
			throw re;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return memo;
	}
	
	public M over(Executor exec, Collection<E> c) {
		return paraInnerOver(memoStart(), exec, c);
	}

	public M into(Executor exec, Collection<E> c, M into) {
		return paraInnerOver(into, exec, c);
	}

	public Reducer<M, M> collator() {
		return collator;
	}

	@Override
	public M reduce(Executor exec, Collection<E> c) {
		return over(exec, c);
	}
	
	@Override
	public M reduce(Executor exec, Collection<E> c, M into) {
		return into(exec, c, into);
	}
}
