package org.funcish.core;

import java.util.Collection;

import org.funcish.Predicator;
import org.funcish.core.coll.ArrayCollection;

public abstract class AbstractPredicator<T> extends AbstractPredicate<T> implements Predicator<T> {

	public AbstractPredicator(Class<T> t) {
		super(t);
	}

	
	protected <C extends Collection<T>> C innerOver(C out, Collection<T> c) {
		int index = 0;
		for(T e : c) {
			try {
				if(test(t().cast(e), index++))
					out.add(e);
			} catch(RuntimeException re) {
				throw re;
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return out;
	}

	public Collection<T> over(Collection<T> c) {
		return innerOver(new ArrayCollection<T>(), c);
	}
	
	public <C extends Collection<T>> C overInto(Collection<T> c, C into) {
		return innerOver(into, c);
	}

}
