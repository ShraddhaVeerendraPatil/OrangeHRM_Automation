package com.orangeHRM.test;

import org.testng.annotations.Test;

import com.orangehrm.base.baseClass;

public class dummyclass extends baseClass {
	@Test
	public void dummyTest() {
		
		String title=driver.getTitle();
		assert title.equals("OrangeHRM"):"Test failed- Title is not matching";
		
		System.out.println("Test passed: Title is matching");
	}

}
