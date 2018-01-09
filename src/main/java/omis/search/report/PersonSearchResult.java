package omis.search.report;

import java.io.Serializable;

/** Person search result.
 * @author Ryan Johns
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 13, 2013)
 * @since OMIS 3.0 */
public class PersonSearchResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String middleName;
	private String lastName;
	private String suffixName;
	private Long nameId;
	private Long personId;

	/** constructor for a person search result.
	 * @param nameId id for name.
	 * @param personId id for person.
	 * @param firstName first name.
	 * @param middleName middle name.
	 * @param lastName last name. */
	public PersonSearchResult(final Long nameId,
			final Long personId,
			final String firstName,
			final String middleName,
			final String lastName, 
			final String suffixName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffixName =suffixName;
		this.nameId = nameId;
		this.personId = personId;
	}

	/** sets the nameId.
	 * @param nameId */
	public void setNameId(final Long nameId) { this.nameId = nameId; }

	/** sets person id.
	 * @param personId */
	public void setPersonId(final Long personId) { this.personId = personId; }

	/** sets the firstName.
	 * @param firstName */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/** set the middle name.
	 * @param middleName */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/** sets the last name.
	 * @param lastName */
	public void setLastName(final String lastName) { this.lastName = lastName; }

	/**
	 * Sets the suffix name.
	 *
	 * @param suffixName suffix name
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	/** gets the first name.
	 * @return  first name string. */
	public String getFirstName() { return this.firstName; }

	/** gets the middle name.
	 * @return middle name string. */
	public String getMiddleName() { return this.middleName; }

	/** gets the last name.
	 * @return last name string. */
	public String getLastName() { return this.lastName; }
	
	/**
	 * Gets the suffix name.
	 *
	 * @return the suffix name
	 */
	public String getSuffixName() {
		return this.suffixName;
	}

	/** gets the ID associated with the name record.
	 * @return name Id. */
	public Long getNameId() { return this.nameId; }

	/** gets the ID associated with the person record.
	 * @return person id. */
	public Long getPersonId() { return this.personId; }
} //PersonSearchResult
