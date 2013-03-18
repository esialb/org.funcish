package org.funcish.core.para;

import java.util.concurrent.ExecutorService;

public class ParaExecutors {
	public static final ExecutorService CALLER = new CallerExecutorService();
	
	public static final ExecutorService AVAILABLE_X1 = new AvailableThreadPoolExecutor(1);
	public static final ExecutorService AVAILABLE_X2 = new AvailableThreadPoolExecutor(2);
	public static final ExecutorService AVAILABLE_X3 = new AvailableThreadPoolExecutor(3);
	public static final ExecutorService AVAILABLE_X4 = new AvailableThreadPoolExecutor(4);
	
	private ParaExecutors() {}
}
