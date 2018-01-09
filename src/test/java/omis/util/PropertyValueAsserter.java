package omis.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Asserter for property values.
 * 
 * <p>An example of usage is:
 * 
 * <pre>
 * PropertyValueAsserter.create()
 *     .addExpectedValue("name", name)
 *     .addExpectedValue("description", description)
 *     .addExpectedValue("sortOrder", sortOrder)
 *     .addExpectedValue("parent.name", parentName)
 *     .performAssertions(entity);
 * </pre>
 * 
 * <p>Nested properties are allowed as in the case of
 * {@code entity.parent.name} above.
 *
 * <p>This utility depends on the entity conforming to JavaBean standards.
 * Specifically, entities must be {@code Serializable} and their properties
 * - including nested properties - must have getter methods. Further, property
 * names are specified in camelCase, all properties must also be
 * {@code Serializable} (according to application standards, wrapper types
 * are used over primitives, for instance).
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class PropertyValueAsserter {

	// Message for failed assertion
	private static final String MESSAGE = "Wrong %s - %s expected; %s found";

	// Prefix of getter methods
	private static final String GETTER_PREFIX = "get";
	
	// Expected property values
	private Map<String, Serializable> expectedPropertyValues
		= new HashMap<String, Serializable>();

	// Hidden constructor
	private PropertyValueAsserter() {
		// Default instantiation
	}

	/**
	 * Instantiates and returns property asserter.
	 * 
	 * @return property asserter
	 */
	public static PropertyValueAsserter create() {
		return new PropertyValueAsserter();
	}
	
	/**
	 * Adds expected property value.
	 * 
	 * @param propertyName property name
	 * @param value value
	 * @return {@code this}
	 */
	public PropertyValueAsserter addExpectedValue(
			final String propertyName, final Serializable value) {
		if (propertyName.isEmpty()) {
			throw new IllegalArgumentException("Property name required");
		}
		expectedPropertyValues.put(propertyName, value);
		return this;
	}
	
	/**
	 * Performs assertions.
	 * 
	 * <p>Assertions are performed per property and expected value.
	 * 
	 * <p>Assertions are null safe.
	 * 
	 * @param serializable object on which to perform assertion
	 */
	public void performAssertions(final Serializable serializable) {
		for (String propertyName : this.expectedPropertyValues.keySet()) {
			Serializable value = null;
			for (String subPropertyName : propertyName.split("\\.")) {
				String methodName
					= GETTER_PREFIX + this.capitalize(subPropertyName);
				Method method;
				try {
					if (value != null) {
						method = value.getClass().getMethod(methodName);
					} else {
						method = serializable.getClass().getMethod(methodName);
					}
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(
							String.format(
									"Method not found - %s; property %s",
									methodName, propertyName), e);
				} catch (SecurityException e) {
					throw new RuntimeException(e);
				}
				try {
					if (value != null) {
						value = (Serializable) method.invoke(value);
					} else {
						value = (Serializable) method.invoke(serializable);
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
			Serializable expectedValue = this.expectedPropertyValues.get(
					propertyName);
			assert expectedValue == null && value == null
					|| (expectedValue != null && expectedValue.equals(value))
				: String.format(MESSAGE, propertyName, expectedValue, value);
		}
	}
	
	// Upper case the first character of text
	private String capitalize(final String text) {
		if (text != null && text.length() > 0) {
			return text.substring(0, 1).toUpperCase() + (text.length() > 1
					? text.substring(1, text.length()) : "");
		} else {
			return text;
		}
	}
}