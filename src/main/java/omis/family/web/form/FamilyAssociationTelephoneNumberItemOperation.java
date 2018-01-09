package omis.family.web.form;

/**
 * Operation to perform on family association telephone number item.
 *
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.0.1 (Dec 3, 2015)
 * @since OMIS 3.0
 */
public enum FamilyAssociationTelephoneNumberItemOperation {

	/** Create.	 */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE,
	
	/** Edit. */
	EDIT;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}