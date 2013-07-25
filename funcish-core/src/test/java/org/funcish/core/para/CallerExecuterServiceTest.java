package org.funcish.core.para;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;

import org.junit.Assert;
import org.junit.Test;

public class CallerExecuterServiceTest {
	@Test
	public void testExecute() throws Exception {
		FutureTask<Thread> ct = new FutureTask<Thread>(new Callable<Thread>() {
			@Override
			public Thread call() throws Exception {
				return Thread.currentThread();
			}
		});
		ExecutorService exec = new CallerExecutorService();
		exec.execute(ct);
		Assert.assertEquals(Thread.currentThread(), ct.get());
	}
	
	@Test
	public void testShutdown() {
		ExecutorService exec = new CallerExecutorService();
		exec.shutdown();
		try {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					Assert.fail();
				}
			});
			Assert.fail();
		} catch(RejectedExecutionException expected) {
		}
	}
}
