package omis.offenderrelationship.report;

import java.io.Serializable;

/**
 * Offender relationship summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2015)
 * @since OMIS 3.0
 */
public class OffenderRelationshipsSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long associationId;
	
	private final Long personId;
	
	private final String personLastName;
	
	private final String personFirstName;
	
	private final String personMiddleName;
	
	private final String criminalAssociationCategoryName;
	
	private final String familyAssociationCategoryName;
	
	private final String visitorAssociationCategoryName;

	/**
	 * Instantiates an offender relationship summary with the
	 * specified properties.
	 * 
	 * @param associationId association id
	 * @param personId person id
	 * @param personLastName person last name
	 * @param personFirstName person first name
	 * @param personMiddleName person middle name
	 * @param criminalAssociationCategoryName criminal association category
	 * name
	 * @param familyAssociationCategoryName family association category name
	 * @param visitorAssociationCategoryName visitor association category name
	 */
	public OffenderRelationshipsSummary(final Long associationId, 
			final Long personId, final String personLastName, 
			final String personFirstName, final String personMiddleName,
			final String criminalAssociationCategoryName,
			final String familyAssociationCategoryName,
			final String visitorAssociationCategoryName) {
		this.associationId = associationId;
		this.personId = personId;
		this.personLastName = personLastName;
		this.personFirstName = personFirstName;
		this.personMiddleName = personMiddleName;
		this.criminalAssociationCategoryName = criminalAssociationCategoryName;
		this.familyAssociationCategoryName = familyAssociationCategoryName;
		this.visitorAssociationCategoryName = visitorAssociationCategoryName;
	}

	/**
	 * Returns the association id.
	 * 
	 * @return association id
	 */
	public Long getAssociationId() {
		return this.associationId;
	}

	/**
	 * Returns the person id.
	 * 
	 * @return person id
	 */
	public Long getPersonId() {
		return this.personId;
	}

	/**
	 * Returns the person last name.
	 * 
	 * @return last name
	 */
	public String getPersonLastName() {
		return this.personLastName;
	}

	/**
	 * Returns the person first name.
	 * 
	 * @return person first name
	 */
	public String getPersonFirstName() {
		return this.personFirstName;
	}

	/**
	 * Returns the person middle name.
	 * 
	 * @return person middle name
	 */
	public String getPersonMiddleName() {
		return this.personMiddleName;
	}

	/**
	 * Returns the criminal association category name.
	 * 
	 * @return criminal association category name
	 */
	public String getCriminalAssociationCategoryName() {
		return this.criminalAssociationCategoryName;
	}

	/**
	 * Returns the family association category name.
	 * 
	 * @return family association category name
	 */
	public String getFamilyAssociationCategoryName() {
		return this.familyAssociationCategoryName;
	}

	/**
	 * Returns the visitor association category name.
	 * 
	 * @return visitor association category name
	 */
	public String getVisitorAssociationCategoryName() {
		return this.visitorAssociationCategoryName;
	}
}