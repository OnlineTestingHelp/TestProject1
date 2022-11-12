package com.testng.practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerLogic.class)
public class RetryExample {
	int i=1;

	@Test(retryAnalyzer = retry.class)
	public void test() {
		
		if(i<=15) {
			i=i+1;
			Assert.fail();
		}else {
			Assert.assertTrue(true);
		}
		
	}

}
