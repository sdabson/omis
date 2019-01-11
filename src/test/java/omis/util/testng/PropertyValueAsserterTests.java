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
package omis.util.testng;

import java.io.Serializable;

import org.testng.annotations.Test;

import omis.util.PropertyValueAsserter;

/**
 * Tests property value asserter.
 *  
 * @author Stephen Abson
 * @version 0.0.1 (Nov 30, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"util"})
public class PropertyValueAsserterTests {

	/** Mock component for testing property value asserter. */
	public static class Component implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		/** Nested mock component for testing property value asserter. */
		public static class NestedComponent implements Serializable {
			
			private static final long serialVersionUID = 1L;
			
			private final String nestedValue;
			
			/**
			 * Instantiates nested component.
			 * 
			 * @param nestedValue nested value
			 */
			private NestedComponent(final String nestedValue) {
				this.nestedValue = nestedValue;
			}
			
			/**
			 * Returns nested value.
			 * 
			 * @return nested value
			 */
			public String getNestedValue() {
				return this.nestedValue;
			}
		}
		
		private final NestedComponent nestedComponent;
		
		private final String value;
			
		/**
		 * Instantiates mock component.
		 * 
		 * @param nestedComponent nested component
		 * @param value value
		 */
		private Component(
				final NestedComponent nestedComponent, final String value) {
			this.nestedComponent = nestedComponent;
			this.value = value;
		}
		
		/**
		 * Returns nested component.
		 * 
		 * @return nested component
		 */
		public NestedComponent getNestedComponent() {
			return this.nestedComponent;
		}
		
		/**
		 * Returns value.
		 * 
		 * @return value
		 */
		public String getValue() {
			return this.value;
		}
	}
	
	/** Tests successful assertion.*/
	public void testSuccess() {
		PropertyValueAsserter.create()
			.addExpectedValue("value", "component")
			.performAssertions(new Component(null, "component"));
	}
	
	/** Tests successful assertion with {@code null} value. */
	public void testSuccessWithNulls() {
		PropertyValueAsserter.create()
			.addExpectedValue("value", null)
			.performAssertions(new Component(null, null));
	}
	
	/** Test nested successful assertion. */
	public void testNestedSuccess() {
		PropertyValueAsserter.create()
			.addExpectedValue("nestedComponent.nestedValue", "nestedComponent")
			.performAssertions(
					new Component(
							new Component.NestedComponent("nestedComponent"),
							null));
	}
	
	/** Tests that {@code AssertionError} is thrown when tests fail. */
	@Test(expectedExceptions = {AssertionError.class})
	public void testFailure(){
		PropertyValueAsserter.create()
			.addExpectedValue("value", "otherComponent")
			.performAssertions(new Component(null, "component"));
	}
	
	/**
	 * Tests that {@code AssertionError} is thrown when {@code null} value is
	 * asserted to be not {@code null}.
	 */
	@Test(expectedExceptions = {AssertionError.class})
	public void testFailureWithNullExpectedValue() {
		PropertyValueAsserter.create()
			.addExpectedValue("value", null)
			.performAssertions(new Component(null, "component"));
	}
	
	/**
	 * Tests that {@code AssertionError} is thrown when none-{@code null} value
	 * is asserted to be {@code null}. 
	 */
	@Test(expectedExceptions = {AssertionError.class})
	public void testFailureWithNullAssertedValue() {
		PropertyValueAsserter.create()
			.addExpectedValue("value", "component")
			.performAssertions(new Component(null, null));
	}
	
	/**
	 * Tests that asserter throws an {@code AssertionError} when nested tests
	 * fail.
	 */
	@Test(expectedExceptions = {AssertionError.class})
	public void testNestedFailure() {
		PropertyValueAsserter.create()
		.addExpectedValue("nestedComponent.nestedValue", "otherNestedComponent")
		.performAssertions(
				new Component(
						new Component.NestedComponent("nestedComponent"),
						null));
	}
	
	/**
	 * Tests the {@code NullPointerException} is thrown if the property name
	 * is {@code null}.
	 */
	@Test(expectedExceptions = {NullPointerException.class})
	public void testPropertyNameRequiredWhenNull() {
		PropertyValueAsserter.create()
			.addExpectedValue(null, "component");
	}
	
	/**
	 * Tests that {@code IllegalArgumentException} is thrown if property name
	 * is empty ({@code ""}).
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testPropertyNameRequiredWhenEmpty() {
		PropertyValueAsserter.create()
			.addExpectedValue("", "component");
	}
	
	/**
	 * Tests that {@code IllegalArgumentException} is thrown when a supplied
	 * method does not exist.
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptonWhenMethodDoesNotExist() {
		PropertyValueAsserter.create()
			.addExpectedValue("madeUpProperty", "madeUpProperty")
			.performAssertions(new Component(null, "property"));
	}
	
	/**
	 * Tests that {@code IllegalArgumentException} is thrown when a nested
	 * supplied method does not exist.
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptionWhenNestedMethodDoesNotExist() {
		PropertyValueAsserter.create()
			.addExpectedValue("nestedComponent.madeUpProperty",
					"madeUpNestedProperty")
			.performAssertions(new Component(
					new Component.NestedComponent("nestedProperty"),
					"madeUpNestedProperty"));
	}
	
	/**
	 * Tests that {@code IllegalStateException} is thrown when no expected
	 * property values are supplied.
	 */
	@Test(expectedExceptions = {IllegalStateException.class})
	public void testIllegalStateExceptionWhenExpectedValuesDoNotExist() {
		PropertyValueAsserter.create().performAssertions(
				"Any Serializable will do!");
	}
}