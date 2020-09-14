package com.Test_ng;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;

public class group_in_group_1 {

@Test(groups= {"football"})
public void test_1() {System.out.println("test_1 > football");}
@Test(groups= {"cricket"})
public void test_2() {System.out.println("test_2 > cricket");}
@Test(groups = {"polo"})
public void test_3() {System.out.println("test_3 > polo");}
@Test(groups= {"football"})
public void test_4() {System.out.println("test_4 > football");}

@Test(groups= {"cricket"})
public void test_5() {System.out.println("test_5 > cricket");}

@Test(groups = {"polo"})
public void test_6() {System.out.println("test_6 > polo");}

@Test(groups= {"football"})
public void test_7() {System.out.println("test_7 > football");}

@Test(groups= {"cricket"})
public void test_8() {System.out.println("test_8 > cricket");}

@Test(groups = {"polo"})
public void test_9() {System.out.println("test_9 > polo");}

@BeforeGroups({"cricket","polo"})
public void beforeCricket() {
	System.out.println("I am beforeGroups:> for cricket");
	
}

}
