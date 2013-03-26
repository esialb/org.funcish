package org.funcish.google;

import org.funcish.core.fn.Mappicator;
import org.funcish.core.fn.Mapping;

import com.google.common.base.Function;

public interface DualMapping<F, T> extends Mappicator<F, T>, Function<F, T>{

}
