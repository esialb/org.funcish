package org.funcish.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.funcish.core.fn.MultiMapper;
import org.funcish.core.fn.MultiReceiver;
import org.junit.Assert;
import org.junit.Test;

public class AbstractMultiMapperTest {
	@Test
	public void testMap() {
		MultiMapper<Integer, Integer> mm = new AbstractMultiMapper<Integer, Integer>(Integer.class, Integer.class) {
			@Override
			public void map0(Integer key, MultiReceiver<Integer> receiver, Integer index) throws Exception {
				receiver.receive(key * 2);
				receiver.receive(key * 4);
			}
		};
		
		List<Integer> result = mm.map(Arrays.asList(1)).into(new ArrayList<Integer>());
		Assert.assertEquals(Arrays.asList(2,4), result);
	}
}
