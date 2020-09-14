package demo2b.DemoTestNG;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestRun {
	
	@Test
	public void verifyTest() {
		
		System.out.println("Welcome TestNG");
	}
	@Test
	public void verifyTest1() {
		assertEquals(false, true);
	}

}
