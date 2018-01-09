package omis.web.validator;

import org.springframework.validation.Errors;

/**
 * String length checks.
 *
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.0.2 (Nov 3, 2016)
 * @since OMIS 3.0
 */
public class StringLengthChecks {
	
	/**
	 * Check for string length.
	 * 
	 * <p>Allows string length validation message to be added to result if
	 * length is exceeded.
	 * 
	 * @author Stephen Abson
	 * @version 0.0.1 (May 29, 2015)
	 * @since OMIS 3.0
	 */
	public static class StringLengthCheck {
		
		private final String messageKey;
		
		private final int length;
		
		// Instantiates string length check
		private StringLengthCheck(
				final String messageKey, final int length) {
			this.messageKey = messageKey;
			this.length = length;
		}
		
		/**
		 * Checks length of string.
		 * 
		 * <p>Adds errors if length is exceeded for field.
		 * 
		 * @param fieldName name of field
		 * @param fieldValue field value; null safe
		 * @param errors errors
		 */
		public void check(final String fieldName, final String fieldValue,
				final Errors errors) {
			if (fieldValue != null && fieldValue.length() > this.length) {
				errors.rejectValue(fieldName, this.messageKey);
			}
		}
		
		/**
		 * Returns length.
		 * 
		 * @return length
		 */
		public int getLength() {
			return this.length;
		}
	}
	
	private final StringLengthCheck verySmallCheck;
	
	private final StringLengthCheck smallCheck;
	
	private final StringLengthCheck mediumCheck;
	
	private final StringLengthCheck largeCheck;
	
	private final StringLengthCheck veryLargeCheck;
	
	private final StringLengthCheck hugeCheck;
	
	private final StringLengthCheck veryHugeCheck;
	
	private final StringLengthCheck humongousCheck;
	
	private final StringLengthCheck documentCheck;

	/**
	 * Instantiates with message key/length pairs.
	 * 
	 * @param verySmallMessageKey very small message key
	 * @param verySmallLength very small length
	 * @param smallMessageKey small message key
	 * @param smallLength small length
	 * @param mediumMessageKey medium message key
	 * @param mediumLength medium length
	 * @param largeMessageKey large message key
	 * @param largeLength large length
	 * @param veryLargeMessageKey very large message key
	 * @param veryLargeLength very large length
	 * @param hugeMessageKey huge message key
	 * @param hugeLength huge length
	 * @param veryHugeMessageKey "very huge" message key
	 * @param veryHugeLength "very huge" length
	 * @param humongousMessageKey humongous message key
	 * @param humongousLength humoungous length
	 */
	public StringLengthChecks(
			final String verySmallMessageKey, final int verySmallLength,
			final String smallMessageKey, final int smallLength,
			final String mediumMessageKey, final int mediumLength,
			final String largeMessageKey, final int largeLength,
			final String veryLargeMessageKey, final int veryLargeLength,
			final String hugeMessageKey, final int hugeLength,
			final String veryHugeMessageKey, final int veryHugeLength,
			final String humongousMessageKey, final int humongousLength,
			final String documentMessageKey, final int documentLength) {
		this.verySmallCheck = new StringLengthCheck(
				verySmallMessageKey, verySmallLength);
		this.smallCheck = new StringLengthCheck(
				smallMessageKey, smallLength);
		this.mediumCheck = new StringLengthCheck(
				mediumMessageKey, mediumLength);
		this.largeCheck = new StringLengthCheck(
				largeMessageKey, largeLength);
		this.veryLargeCheck = new StringLengthCheck(
				veryLargeMessageKey, veryLargeLength);
		this.hugeCheck = new StringLengthCheck(
				hugeMessageKey, hugeLength);
		this.veryHugeCheck = new StringLengthCheck(
				veryHugeMessageKey, veryHugeLength);
		this.humongousCheck = new StringLengthCheck(
				humongousMessageKey, humongousLength);
		this.documentCheck = new StringLengthCheck(
				documentMessageKey, documentLength);
	}

	/**
	 * Instantiates with checks.
	 * 
	 * @param verySmallCheck very small check
	 * @param smallCheck small check
	 * @param mediumCheck medium check
	 * @param largeCheck large check
	 * @param veryLargeCheck very large check
	 * @param hugeCheck huge check
	 * @param veryHugeCheck "very huge" check
	 * @param humongousCheck humongous check
	 * @param documentCheck document check
	 */
	public StringLengthChecks(
			final StringLengthCheck verySmallCheck,
			final StringLengthCheck smallCheck,
			final StringLengthCheck mediumCheck,
			final StringLengthCheck largeCheck,
			final StringLengthCheck veryLargeCheck,
			final StringLengthCheck hugeCheck,
			final StringLengthCheck veryHugeCheck,
			final StringLengthCheck humongousCheck,
			final StringLengthCheck documentCheck) {
		this.verySmallCheck = verySmallCheck;
		this.smallCheck = smallCheck;
		this.mediumCheck = mediumCheck;
		this.largeCheck = largeCheck;
		this.veryLargeCheck = veryLargeCheck;
		this.hugeCheck = hugeCheck;
		this.veryHugeCheck = veryHugeCheck;
		this.humongousCheck = humongousCheck;
		this.documentCheck = documentCheck;
	}
	
	/**
	 * Returns very small check.
	 * 
	 * @return very small check
	 * @throws UnsupportedOperationException if very small check is not
	 * supported
	 */
	public StringLengthCheck getVerySmallCheck() {
		if (this.verySmallCheck != null) { 
			return this.verySmallCheck;
		} else {
			throw new UnsupportedOperationException(
					"Very small string length check not supported");
		}
	}
	
	/**
	 * Returns small check.
	 * 
	 * @return small check
	 * @throws UnsupportedOperationException if small check is not supported
	 */
	public StringLengthCheck getSmallCheck() {
		if (this.smallCheck != null) {
			return this.smallCheck;
		} else {
			throw new UnsupportedOperationException(
					"Small string length check not supported");
		}
	}
	
	/**
	 * Returns medium check.
	 * 
	 * @return medium check
	 * @throws UnsupportedOperationException if medium check is not supported
	 */
	public StringLengthCheck getMediumCheck() {
		if (this.mediumCheck != null) {
			return this.mediumCheck;
		} else {
			throw new UnsupportedOperationException(
					"Medium string length check not supported");
		}
	}
	
	/**
	 * Returns large check.
	 * 
	 * @return large check
	 * @throws UnsupportedOperationException if large check is not supported
	 */
	public StringLengthCheck getLargeCheck() {
		if (this.largeCheck != null) {
			return this.largeCheck;
		} else {
			throw new UnsupportedOperationException(
					"Large string length check not supported");
		}
	}
	
	/**
	 * Returns very large check.
	 * 
	 * @return very large check
	 * @throws UnsupportedOperationException if very large check is not
	 * supported
	 */
	public StringLengthCheck getVeryLargeCheck() {
		if (this.veryLargeCheck != null) {
			return this.veryLargeCheck;
		} else {
			throw new UnsupportedOperationException(
					"Very large string length check not supported");
		}
	}
	
	/**
	 * Returns huge check.
	 * 
	 * @return huge check
	 * @throws UnsupportedOperationException if huge check is not supported
	 */
	public StringLengthCheck getHugeCheck() {
		if (this.hugeCheck != null) {
			return this.hugeCheck;
		} else {
			throw new UnsupportedOperationException(
					"Huge string length check not supported");
		}
	}
	
	/**
	 * Returns "very huge" check.
	 * 
	 * @return "very huge" check
	 * @throws UnsupportedOperationException if "very huge" check is not
	 * supported
	 */
	public StringLengthCheck getVeryHugeCheck() {
		if (this.veryHugeCheck != null) {
			return this.veryHugeCheck;
		} else {
			throw new UnsupportedOperationException(
					"Very huge string length check not supported");
		}
	}
	
	/**
	 * Returns humongous check.
	 * 
	 * @return humongous check
	 * @throws UnsupportedOperationException if humongous check is not supported
	 */
	public StringLengthCheck getHumongousCheck() {
		if (this.humongousCheck != null) {
			return this.humongousCheck;
		} else {
			throw new UnsupportedOperationException(
					"Humongous string check not supported");
		}
	}
	
	/**
	 * Returns document check.
	 * 
	 * @return document check
	 * @throws UnsupportedOperationExteption if document check is 
	 * not supported
	 */
	public StringLengthCheck getDocumentCheck() {
		if (this.documentCheck != null) {
			return this.documentCheck;
		} else {
			throw new UnsupportedOperationException(
					"Document string check not supported");
		}
	}
}