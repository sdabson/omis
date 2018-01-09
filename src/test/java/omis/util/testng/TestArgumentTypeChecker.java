package omis.util.testng;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import omis.util.ArgumentTypeChecker;

import org.testng.annotations.Test;

/**
 * Tests the argument type checker.
 * @author Stephen Abson
 * @version 0.1.0 (Oct 31, 2012)
 * @since OMIS 3.0
 */
@Test(groups = { "argumentTypeChecker" })
public class TestArgumentTypeChecker {

	/** Tests a correct single type with a single argument. */
	public void testCorrectSingleTypeForSingleArgument() {
		ArgumentTypeChecker.createInstance().setArguments(new Integer(42))
				.setTypes(Integer.class).check();
	}
	
	/**
	 * Tests an incorrect single type with a single argument throws an
	 * @{code IllegalArgumentException}.
	 * 
	 * @throws IllegalArgumentException if the type and argument mismatch as
	 * expected
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectSingleTypeForSingleArgumentThrowsException() {
		ArgumentTypeChecker.createInstance().setArguments("42")
				.setTypes(Integer.class).check();
	}
	
	/** Tests a correct single type for multiple arguments. */
	public void testCorrectSingleTypeForMultipleArguments() {
		ArgumentTypeChecker.createInstance().setArguments(
				2, 3, 5, 7, 11, 13, 17, 19)
			.setTypes(Integer.class)
			.check();
	}
	
	/**
	 * Tests that an incorrect single type for multiple arguments throws
	 * an {@code IllegalArgumentException}.
	 * 
	 * @throws IllegalArgumentException if the single type and arguments
	 * mismatch as expected
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectSingleTypeForMultipleArgumentsThrowsException() {
		ArgumentTypeChecker.createInstance().setArguments(
				2, "5", false, java.util.Collections.emptyList())
			.setTypes(Integer.class)
			.check();
	}
	
	/** Tests that correct multiple types for multiple arguments. */
	public void testCorrectMultipleTypesForMultipleArguments() {
		ArgumentTypeChecker.createInstance().setArguments(
				2, "42", false, Collections.emptyList())
			.setTypes(Integer.class, String.class, Boolean.class, List.class)
			.check();
	}
	
	/**
	 * Tests that correct multiple types for multiple arguments with
	 * covariance (using base types of arguments). */
	public void testCorrectMultipleTypesForMultipleArgumentsWithCovariance() {
		ArgumentTypeChecker.createInstance().setArguments(
					2, "42", false, Collections.emptyList())
			.setTypes(Number.class, Object.class, Serializable.class,
					Collection.class)
			.check();
	}
	
	/**
	 * Tests that incorrect multiple types for multiple arguments throws an
	 * {@code IllegalArgumentException}.
	 * @throws IllegalArgumentException if the multiple arguments are not
	 * of the multiple types given as expected
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectMultipeTypesForMultipleArguments() {
		ArgumentTypeChecker.createInstance().setArguments(
				2, "42", false, Collections.emptySet())
			.setTypes(Integer.class, String.class, Boolean.class, List.class)
			.check();
	}
	
	/**
	 * Tests that an illegal argument exception is thrown when both the number
	 * of types and number of arguments is greater than one but are not equal.
	 * <p>
	 * In this case, there are more arguments than types.
	 * @throws IllegalArgumentException if the number of both the types and
	 * arguments is greater than one but are not equal
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testArgumentAndTypeLengthMismatchWithMoreArguments() {
		ArgumentTypeChecker.createInstance().setArguments(2, "42", false)
			.setTypes(Integer.class, String.class)
			.check();
	}
	
	/**
	 * Tests that an illegal argument exception is thrown when both the number
	 * of types and number of arguments is greater than one but are not equal.
	 * <p>
	 * In this case, there are more types than arguments.
	 * @throws IllegalArgumentException if the number of both the types and
	 * arguments is greater than one but are not equal
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testArgumentAndTypeLengthMismatchWithMoreTypes() {
		ArgumentTypeChecker.createInstance().setArguments(2, "42")
			.setTypes(Integer.class, String.class, Boolean.class)
			.check();
	}
}