package omis.web.util;

/**
 * Checks a set of fields for changes in values.
 *
 * <p>Checked fields are {@code null} safe. 
 * 
 * <p>Multiple checks can be chained together in one statement, typically
 * like the following:
 * 
 * <pre>
 * if (FieldSetChangeChecker.createIfNotChangedInstance()
 *         .check(entity.getProperty1(), form.getField1())
 *         .check(entity.getProperty2(), form.getField2())
 *         .check(entity.getProperty3(), form.getField3())
 *         .isChanged()) {
 *     
 *     // Field or field(s) changed
 * }
 * </pre>
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Sep 12, 2015)
 * @since OMIS 3.0
 */
public abstract class FieldSetChangeChecker {

	// Whether change has been made
	private boolean changed;
	
	// Hidden constructor
	private FieldSetChangeChecker(final boolean changed) {
		this.changed = changed;
	}
	
	/**
	 * Returns new checker.
	 * 
	 * <p>This checker will only check if a change was not previously made. That
	 * is, the first comparison to result in a change being detected will
	 * prevent other comparisons being made. This is an optimization for cases
	 * where a single change on a set of fields is required to perform an
	 * action.
	 * 
	 * @return new checker
	 */
	public static FieldSetChangeChecker createIfNotChangedInstance() {
		return new FieldSetChangeChecker(false) {

			/** {@inheritDoc} */
			@Override
			public FieldSetChangeChecker check(
					final Object oldValue, final Object newValue) {
				if (!this.isChanged()) {
					this.setChanged(true);
				}
				return this;
			}
		};
	}
	
	/**
	 * Checks whether value has changed.
	 * 
	 * @param oldValue old value
	 * @param newValue new value
	 * @return whether value has changed
	 */
	public abstract FieldSetChangeChecker check(
			final Object oldValue, final Object newValue);
	
	/**
	 * Returns whether changed.
	 * 
	 * @return whether changed
	 */
	public boolean isChanged() {
		return this.changed;
	}
	
	/**
	 * Sets whether changed.
	 * 
	 * @param changed whether changed
	 */
	protected void setChanged(final boolean changed) {
		this.changed = changed;
	}
	
	/**
	 * Implementation of check method.
	 * 
	 * @param oldValue old value
	 * @param newValue new value
	 * @return whether values are changed
	 */
	protected boolean checkImpl(final Object oldValue, final Object newValue) {
		 if (oldValue != null) {
			 return oldValue.equals(newValue);
		 } else if (newValue != null) {
			 return true;
		 }
		 return false;
	}
}