package org.funcish;

import java.util.Collection;

public interface Predicator<T> extends Predicate<T>, Applicator<T, Collection<T>, Boolean> {

}
