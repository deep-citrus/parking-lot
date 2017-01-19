package com.gojek.park.util;

import org.junit.Assert;

import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void testGetInteger() {
		Assert.assertTrue("Null input to integer value", null == CommonUtil.getInteger(null));
		Assert.assertTrue("Input as 10", 10 == CommonUtil.getInteger("10"));
		Assert.assertTrue("Input as String", null == CommonUtil.getInteger("String"));
	}

}
