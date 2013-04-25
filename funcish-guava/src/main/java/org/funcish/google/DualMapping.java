package org.funcish.google;

import org.funcish.core.fn.Mapper;
import org.funcish.core.fn.Mapping;

import com.google.common.base.Function;

public interface DualMapping<F, T> extends Mapper<F, T>, Function<F, T>{

}
