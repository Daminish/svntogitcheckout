package com.capco.living.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.capco.living.controller.LoginControllerTest;

/**
 * This test case suite class are created to run all test cases
 * @author e5544325
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
   LoginControllerTest.class,
})

public class LivingTestSuite {

}
