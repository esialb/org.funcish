package org.funcish.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class IterablesTest {
	@Test
	public void testInto() {
		List<Integer> ints = Arrays.asList(1,2,3);
		List<Integer> res = new ArrayList<Integer>();
		Iterables.into(ints).into(res);
		Assert.assertEquals(ints, res);
	}
}
