/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.offenseterm.web.form.testng;

import org.testng.annotations.Test;

import omis.offenseterm.web.form.OffenseItemConnection;
import omis.offenseterm.web.form.OffenseItemConnectionClassification;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests instantiation of offense item connection.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Ferb 13, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"web", "offenseTerm"})
public class OffenseItemConnectionConstructorTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Property names. */
	
	private static final String VALUE_PROPERTY_NAME = "value";
	
	private static final String INDEX_PROPERTY_NAME = "index";
	
	private static final String CLASSIFICATION_PROPERTY_NAME = "classification";
	
	/* Classification property names. */
	
	private static final String NON_EXISTENT_CLASSIFICATION_NAME
		= "NON_EXISTENT";
	
	/* Value formats. */
	
	private static final String CONSECUTIVE_VALUE_FORMAT = "%s[%d]";
	
	/* Test cases. */

	/** Tests instantiation of initial connection. */
	public void testInitial() {
		OffenseItemConnectionClassification initialClassification
			= OffenseItemConnectionClassification.INITIAL;
		OffenseItemConnection connection = new OffenseItemConnection(
				initialClassification.getName());
		PropertyValueAsserter.create()
			.addExpectedValue(VALUE_PROPERTY_NAME,
					initialClassification.getName())
			.addExpectedValue(INDEX_PROPERTY_NAME, null)
			.addExpectedValue(CLASSIFICATION_PROPERTY_NAME,
					initialClassification)
			.performAssertions(connection);
	}
	
	/** Tests instantiation of concurrent connection. */
	public void testConcurrent() {
		OffenseItemConnectionClassification concurrentClassification
			= OffenseItemConnectionClassification.CONCURRENT;
		OffenseItemConnection connection = new OffenseItemConnection(
				concurrentClassification.getName());
		PropertyValueAsserter.create()
			.addExpectedValue(VALUE_PROPERTY_NAME,
					concurrentClassification.getName())
			.addExpectedValue(INDEX_PROPERTY_NAME, null)
			.addExpectedValue(CLASSIFICATION_PROPERTY_NAME,
					concurrentClassification)
			.performAssertions(connection);
	}
	
	/** Tests instantiation of consecutive connection. */
	public void testConsecutive() {
		long index = 1000L;
		OffenseItemConnectionClassification consecutiveClassification
			= OffenseItemConnectionClassification.CONSECUTIVE;
		String value = String.format(CONSECUTIVE_VALUE_FORMAT,
				consecutiveClassification.getName(), index);
		OffenseItemConnection connection = new OffenseItemConnection(value);
		PropertyValueAsserter.create()
			.addExpectedValue(VALUE_PROPERTY_NAME, value)
			.addExpectedValue(INDEX_PROPERTY_NAME, index)
			.addExpectedValue(CLASSIFICATION_PROPERTY_NAME,
					consecutiveClassification)
			.performAssertions(connection);
	}
	
	/**
	 * Tests instantiation of connection consecutive to sentence on other
	 * docket.
	 */
	public void testConsecutiveOtherDocket() {
		long index = 2000L;
		OffenseItemConnectionClassification consecutiveOtherDocketClassification
			= OffenseItemConnectionClassification.CONSECUTIVE_OTHER_DOCKET;
		String value = String.format(CONSECUTIVE_VALUE_FORMAT,
				consecutiveOtherDocketClassification.getName(), index);
		OffenseItemConnection connection = new OffenseItemConnection(value);
		PropertyValueAsserter.create()
			.addExpectedValue(VALUE_PROPERTY_NAME, value)
			.addExpectedValue(INDEX_PROPERTY_NAME, index)
			.addExpectedValue(CLASSIFICATION_PROPERTY_NAME,
					consecutiveOtherDocketClassification)
			.performAssertions(connection);
	}
	
	/**
	 * Test that {@code IllegalArgumentException} is thrown if the
	 * classification does not exist.
	 *  
	 * @throws IllegalArgumentException if classification does not exist
	 * - asserted
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptionIfClassificationIsNonexistent() {
		new OffenseItemConnection(String.format(
				CONSECUTIVE_VALUE_FORMAT, NON_EXISTENT_CLASSIFICATION_NAME,
				3000L));
	}
}
