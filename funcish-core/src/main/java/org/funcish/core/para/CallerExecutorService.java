package org.funcish.core.para;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CallerExecutorService extends AbstractExecutorService {
	
	private CountDownLatch latch = new CountDownLatch(1);
	private AtomicInteger tasks = new AtomicInteger(0);
	
	@Override
	public void shutdown() {
		latch.countDown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		shutdown();
		return Collections.emptyList();
	}

	@Override
	public boolean isShutdown() {
		return latch.getCount() > 0;
	}

	@Override
	public boolean isTerminated() {
		synchronized(tasks) {
			return isShutdown() && tasks.get() == 0;
		}
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return latch.await(timeout, unit);
	}

	@Override
	public void execute(Runnable command) {
		synchronized(tasks) {
			if(isShutdown())
				throw new RejectedExecutionException();
			tasks.incrementAndGet();
		}
		try {
			command.run();
		} finally {
			tasks.decrementAndGet();
		}
	}

}
