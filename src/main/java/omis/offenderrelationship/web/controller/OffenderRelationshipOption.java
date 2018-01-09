package omis.offenderrelationship.web.controller;

/**
 * Option of offender relation.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public enum OffenderRelationshipOption {

	/** Criminal associate. */
	CRIMINAL_ASSOCIATE,
	
	/** Family member. */
	FAMILY_MEMBER,
	
	/** Victim. */
	VICTIM,
	
	/** Visitor. */
	VISITOR,
	
	/** New relation. */
	NEW_RELATION;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}