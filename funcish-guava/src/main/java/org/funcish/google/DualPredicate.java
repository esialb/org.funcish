package org.funcish.google;

import org.funcish.core.fn.Predicate;

public interface DualPredicate<T> extends Predicate<T>, com.google.common.base.Predicate<T> {

}
