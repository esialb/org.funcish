package org.funcish.clj;

import java.util.Arrays;

import org.funcish.core.fn.Function;
import org.funcish.core.impl.ProxyFunction;

import clojure.lang.AFn;
import clojure.lang.IFn;
import clojure.lang.ISeq;
import clojure.lang.Util;

public class ProxyFunctionIFn<T> extends ProxyFunction implements IFn {

	public ProxyFunctionIFn(Function<T> target) {
		super(target);
	}

	public Function<T> typed() {
		return (Function<T>) this;
	}
	
	protected Object sneakyCall(Object... args) {
		try {
			return call(args);
		} catch (Exception ex) {
			throw Util.sneakyThrow(ex);
		}
	}

	@Override
	public void run() {
		sneakyCall();
	}

	@Override
	public Object invoke() {
		return sneakyCall();
	}

	@Override
	public Object invoke(Object arg1) {
		return sneakyCall(arg1);
	}

	@Override
	public Object invoke(Object arg1, Object arg2) {
		return sneakyCall(arg1, arg2);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3) {
		return sneakyCall(arg1, arg2, arg3);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4) {
		return sneakyCall(arg1, arg2, arg3, arg4);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13, Object arg14) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17, Object arg18) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17,
			Object arg18, Object arg19) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17,
			Object arg18, Object arg19, Object arg20) {
		return sneakyCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
	}

	@Override
	public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
			Object arg5, Object arg6, Object arg7, Object arg8, Object arg9,
			Object arg10, Object arg11, Object arg12, Object arg13,
			Object arg14, Object arg15, Object arg16, Object arg17,
			Object arg18, Object arg19, Object arg20, Object... args) {
		Object[] first20 = new Object[] {arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20};
		Object[] all = Arrays.copyOf(first20, first20.length + args.length);
		System.arraycopy(args, 0, all, first20.length, args.length);
		return sneakyCall(all);
	}

	@Override
	public Object applyTo(ISeq arglist) {
		return AFn.applyToHelper(this, arglist);
	}

}
