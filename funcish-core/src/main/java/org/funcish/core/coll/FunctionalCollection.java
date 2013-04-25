package org.funcish.core.coll;

import java.util.Collection;

import org.funcish.core.fn.Mapper;
import org.funcish.core.fn.Mapping;
import org.funcish.core.fn.Predicate;
import org.funcish.core.fn.Predicator;
import org.funcish.core.fn.Reducer;
import org.funcish.core.fn.Reduction;
import org.funcish.core.fn.Sequencer;

/**
 * {@link Collection} with additional helper methods to process itself using
 * {@link Mapping}, {@link Predicate}, and {@link Reduction} functions.
 * @author robin
 *
 * @param <E>
 */
public interface FunctionalCollection<E> extends Collection<E> {
	
	public Class<E> e();
	
	/**
	 * Return a new {@link FunctionalCollection} obtained by applying
	 * the argument {@link Mapping} to this object.
	 * @param m
	 * @return
	 * @see Mapper#map(Collection)
	 */
	public <V> FunctionalCollection<V> map(Mapping<? super E, V> m);
	/**
	 * Apply the argument {@link Mapping} to this object, adding the results
	 * to argument {@link Collection}.
	 * @param m
	 * @param into
	 * @return
	 * @see Mapper#map(Collection, Collection)
	 */
	public <V, C extends Collection<? super V>> C map(Mapping<? super E, V> m, C into);
	
	/**
	 * Return a new {@link FunctionalCollection} obtained by applying
	 * the argument {@link Predicate} to this object.
	 * @param p
	 * @return
	 * @see Predicator#filter(Collection)
	 */
	public FunctionalCollection<E> filter(Predicate<? super E> p);
	/**
	 * Apply the argument {@link Predicate} to this object, adding the results
	 * to the argument {@link Collection}
	 * @param p
	 * @param into
	 * @return
	 * @see Predicator#filter(Collection, Collection)
	 */
	public <C extends Collection<? super E>> C filter(Predicate<? super E> p, C into);
	
	/**
	 * Reduce this {@link FunctionalCollection}
	 * @param r
	 * @return
	 * @see Reducer#reduce(Collection)
	 */
	public <M> M reduce(Reduction<? super E, M> r);
	
	public Sequencer<E> seq();
}
