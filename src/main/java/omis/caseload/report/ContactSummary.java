package omis.caseload.report;

import java.util.Date;

/** Summary for caseload contact.
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0 */
public class ContactSummary {
	private final Long contactId;
	private final Date contactDate;
	private final Boolean activeCaseload;
	private final String contactByCategory;
	private final String contactByLastName;
	private final String contactByFirstName;
	private final String contactByMiddleName;
	
	/** Constructor.
	 * @param contactId - contact id. 
	 * @param contactDate - contact date. 
	 * @param activeCaseload - active caseload. 
	 * @param contactByCategory - contact by category. 
	 * @param contactByLastName - contact by last name. 
	 * @param contactByFirstName - contact by first name.
	 * @param contactByMiddleName - contact by middle name.*/
	public ContactSummary(final Long contactId, 
					final Boolean activeCaseload,
					final Date contactDate,
					final String contactByCategory,
					final String contactByLastName,
					final String contactByFirstName,
					final String contactByMiddleName) {
		this.contactId = contactId;
		this.contactDate = contactDate;
		this.activeCaseload = activeCaseload;
		this.contactByCategory = contactByCategory;
		this.contactByLastName = contactByLastName;
		this.contactByFirstName = contactByFirstName;
		this.contactByMiddleName = contactByMiddleName;
	}
	
	/** Gets contact id.
	 * @return contact id. */
	public Long getContactId() {
		return this.contactId;
	}
	
	/** Gets contact date.
	 * @return contact date. */
	public Date getContactDate() {
		return this.contactDate;
	}
	
	/** Gets active caseload.
	 * @return active caseload. */
	public Boolean getActiveCaseload() {
		return this.activeCaseload;
	}
	
	/** Gets contact by category.
	 * @return contact by category. */
	public String getContactByCategory() {
		return this.contactByCategory;
	}
	
	/** Gets contact by last name.
	 * @return contact by last name. */
	public String getContactByLastName() {
		return this.contactByLastName;
	}
	
	/** Gets contact by first name.
	 * @return contact by first name. */
	public String getContactByFirstName() {
		return this.contactByFirstName;
	}
	
	/** Gets contact by middle name.
	 * @return contact by middle name. */
	public String getContactByMiddleName() {
		return this.contactByMiddleName;
	}
}
