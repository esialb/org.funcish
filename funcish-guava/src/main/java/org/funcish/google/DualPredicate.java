package org.funcish.google;

import org.funcish.core.fn.Predicator;

import com.google.common.base.Predicate;

public interface DualPredicate<T> extends Predicator<T>, Predicate<T> {

}
