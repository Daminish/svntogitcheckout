package com.capco.living.testSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This test case suite runner class are created to run all test cases
 * @author e5544325
 *
 */

public class LivingTestSuiteRunner {
	
	public static void main(String[] args) {
	
	Result result = JUnitCore.runClasses(LivingTestSuite.class);
	
	for (Failure fail : result.getFailures()) {
		System.out.println(fail.toString());
	 }
	
	 if (result.wasSuccessful()) {
		 System.out.println("All tests finished successfully...");
		 }
	  }
}
