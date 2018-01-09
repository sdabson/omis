package omis.workrestriction.report;

import java.io.Serializable;
import java.util.Date;

/**
 * WorkRestrictionSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final String categoryName;
	
	private final String authorizedByLastName;
	
	private final String authorizedByFirstName;
	
	private final String authorizedByMiddleName;
	
	private final String authorizedBySuffix;
	
	private final String authorizedByUserName;
	
	private final Date authorizationDate;
	
	private final String notes;

	/**
	 * Constructor for work restriction summaries
	 * @param id
	 * @param offenderLastName
	 * @param offenderFirstName
	 * @param offenderMiddleName
	 * @param offenderSuffix
	 * @param offenderNumber
	 * @param categoryName
	 * @param authorizedByLastName
	 * @param authorizedByFirstName
	 * @param authorizedByMiddleName
	 * @param authorizedBySuffix
	 * @param authorizedByUserName
	 * @param authorizationDate
	 * @param notes
	 */
	public WorkRestrictionSummary(final Long id, final String offenderLastName, 
			final String offenderFirstName, final String offenderMiddleName,
			final String offenderSuffix, final Integer offenderNumber, 
			final String categoryName, final String authorizedByLastName,
			final String authorizedByFirstName, 
			final String authorizedByMiddleName, final String authorizedBySuffix,
			final String authorizedByUserName, final Date authorizationDate, 
			final String notes) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.categoryName = categoryName;
		this.authorizedByLastName = authorizedByLastName;
		this.authorizedByFirstName = authorizedByFirstName;
		this.authorizedByMiddleName = authorizedByMiddleName;
		this.authorizedBySuffix = authorizedBySuffix;
		this.authorizedByUserName = authorizedByUserName;
		this.authorizationDate = authorizationDate;
		this.notes = notes;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * @return the offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * @return the offenderSuffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}

	/**
	 * @return the offenderNumber
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * @return the authorizedByLastName
	 */
	public String getAuthorizedByLastName() {
		return this.authorizedByLastName;
	}

	/**
	 * @return the authorizedByFirstName
	 */
	public String getAuthorizedByFirstName() {
		return this.authorizedByFirstName;
	}

	/**
	 * @return the authorizedByMiddleName
	 */
	public String getAuthorizedByMiddleName() {
		return this.authorizedByMiddleName;
	}

	/**
	 * @return the authorizedBySuffix
	 */
	public String getAuthorizedBySuffix() {
		return this.authorizedBySuffix;
	}

	/**
	 * @return the authorizedByUserName
	 */
	public String getAuthorizedByUserName() {
		return this.authorizedByUserName;
	}

	/**
	 * @return the authorizationDate
	 */
	public Date getAuthorizationDate() {
		return this.authorizationDate;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return this.notes;
	}
	
	
	
	

}
