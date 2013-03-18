package org.funcish.core.para;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AvailableThreadPoolExecutor extends ThreadPoolExecutor {

	private int threadsPerProcessor;
	
	public AvailableThreadPoolExecutor(int threadsPerProcessor) {
		this(threadsPerProcessor, 30, TimeUnit.SECONDS);
	}
	
	public AvailableThreadPoolExecutor(int threadsPerProcessor, long keepAliveTime, TimeUnit unit) {
		super(1, Integer.MAX_VALUE, keepAliveTime, unit, new LinkedBlockingQueue<Runnable>());
		this.threadsPerProcessor = threadsPerProcessor;
	}
	
	protected void adjustPool() {
		int idealThreads = threadsPerProcessor * Runtime.getRuntime().availableProcessors();
		if(idealThreads > getCorePoolSize())
			setCorePoolSize(getCorePoolSize() + 1);
		else if(idealThreads < getCorePoolSize())
			setCorePoolSize(Math.max(getCorePoolSize() - 1, 1));
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		adjustPool();
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		adjustPool();
	}

}
