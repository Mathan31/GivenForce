package day10;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//@Test -- It can be at class level as well . It will consider all the methods are TestCase .
public class AllAnnotation {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("This is My Before Suite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("This is My Before Test");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("This is My Before Class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This is My Before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This is My After Method");
	}

	@Test(priority = 1)
	public void testcase1() {
		System.out.println("This is My Test Case 1");
		Assert.assertEquals(true, true);
		System.out.println("Rest of Test Case 1");
	}
  
	@Test(priority = 2)
	public static void testcase2() {
		System.out.println("This is My Test Case 2");
		Assert.assertTrue(false);
		System.out.println("Rest of Test Case 2");
	}
	 
	@Test(priority = 3)
	public void testcase3() {
		System.out.println("This is My Test Case 3");
		throw new SkipException("TestCase is Skipped");
	}
	 
	//@Test
	public void testcase4() {
		System.out.println("This is My Test Case 4");
	}
	
	 
	@AfterClass
	public void afterClass() {
		System.out.println("This is My After Class");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("This is My After Test");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("This is My After Suite");
	}
	
	
	
	
	
	
	
	

}
