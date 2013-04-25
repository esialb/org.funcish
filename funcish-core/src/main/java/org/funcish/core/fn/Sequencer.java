package org.funcish.core.fn;

import java.util.List;

public interface Sequencer<E> extends Sequence<E>, AutoIterable<E> {
	
	public boolean hasNext(Integer index);

	public List<E> list();
}
