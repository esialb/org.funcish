package org.funcish.core.impl;

import java.util.Collection;

import org.funcish.core.fn.Predicator;
import org.funcish.core.util.ArrayCollection;

public abstract class AbstractPredicator<T> extends AbstractPredicate<T> implements Predicator<T> {

	public AbstractPredicator(Class<T> t) {
		super(t);
	}

	
	protected <U extends T, C extends Collection<? super U>> C innerOver(C out, Collection<? extends U> c) {
		int index = 0;
		for(U e : c) {
			try {
				if(test0(t().cast(e), index++))
					out.add(e);
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return out;
	}

	@Override
	public Collection<T> over(Collection<? extends T> c) {
		return innerOver(new ArrayCollection<T>(), c);
	}
	
	public <U extends T, C extends Collection<? super U>> C into(Collection<? extends U> c, C into) {
		return innerOver(into, c);
	}

	@Override
	public Collection<T> filter(Collection<? extends T> c) {
		return over(c);
	}
	
	@Override
	public <U extends T, C extends Collection<? super U>> C filter(Collection<? extends U> c, C into) {
		return into(c, into);
	}
}
