package com.Test_ng;

import org.testng.annotations.Factory;

public class TestRunFactory {
	
	@Factory
	public Object[] VerifyFactory() {
		return new Object[] {new TestRun_01("Friday"),new TestRun_01("Saturday")};
	}

}
