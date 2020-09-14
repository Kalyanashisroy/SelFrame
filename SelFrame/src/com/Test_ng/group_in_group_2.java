package com.Test_ng;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class group_in_group_2 {

@Test(groups= {"football"})
public void g2_test_1() {}
@Test(groups= {"cricket"})
public void g2_test_2() {}
@Test(groups = {"polo"})
public void g2_test_3() {}
@Test(groups= {"football"})
public void g2_test_4() {}
@Test(groups= {"cricket"})
public void g2_test_5() {}
@Test(groups = {"polo"})
public void g2_test_6() {}
@Test(groups= {"football"})
public void g2_test_7() {}
@Test(groups= {"cricket"})
public void g2_test_8() {}
@Test(groups = {"polo"})
public void g2_test_9() {}

}
