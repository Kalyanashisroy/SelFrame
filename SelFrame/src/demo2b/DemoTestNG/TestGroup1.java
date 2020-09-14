package demo2b.DemoTestNG;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class TestGroup1 {
	@Test(groups="A")
	public void verifyTest1() {
		System.out.println("Running test1");
	}
	@Test(groups="B")
	public void verifyTest2() {
		System.out.println("Running test2");
	}
	@Test(groups="C")
	public void verifyTest3() {
		System.out.println("Running test3");
	}
	@Test(groups="D")
	public void verifyTest4() {
		System.out.println("Running test4");
	}
	@Test(groups="E")
	public void verifyTest5() {
		System.out.println("Running test5");
	}
	@Test(groups="F")
	public void verifyTest6() {
		System.out.println("Running test6");
	}
	@Test(groups="G")
	public void verifyTest7() {
		System.out.println("Running test7");
	}
	@Test(groups="H")
	public void verifyTest8() {
		System.out.println("Running test8");
	}
	@Test(groups="I")
	public void verifyTest9() {
		System.out.println("Running test9");
	}	
	@BeforeGroups({"D","H"})
	public void verifyBeforeTest() {
		System.out.println("I am before group test");
	}

}
