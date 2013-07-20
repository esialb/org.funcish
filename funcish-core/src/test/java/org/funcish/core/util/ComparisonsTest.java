package org.funcish.core.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ComparisonsTest {
	@Test
	public void testIndexOf() {
		List<Integer> ints = Arrays.asList(1,2,3);
		Comparator<Integer> cmp = Comparisons.indexOf(ints);
		
		Assert.assertTrue(cmp.compare(1, 2) < 0);
		Assert.assertTrue(cmp.compare(3, 1) > 0);
		Assert.assertTrue(cmp.compare(2, 2) == 0);
	}
}
