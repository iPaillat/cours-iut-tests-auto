package com.sqli.isc.iut.courses.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class HelloWorldTest {

	private static final Logger LOGGER = getLogger(HelloWorldTest.class);

	@Test
	public void this_is_my_first_test() {
		LOGGER.debug("** This is my first test with JUnit 5 ***");

		assertTrue(true);
	}

}
