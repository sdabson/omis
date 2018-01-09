package omis.identificationnumber.report;

import java.io.Serializable;
import java.util.Date;

import omis.datatype.DateRange;

/**
 * Identification number summary.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final String issuerName;
	
	private final String categoryName;
	
	private final String value;
	
	private final Date issueDate;
	
	private final Date expireDate;
	
	private final Boolean active;
	
	/**
	 * Instantiates summary of identification number.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderSuffix suffix of offender
	 * @param offenderNumber offender number
	 * @param issuerName name of issuer
	 * @param categoryName name of category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @param effectiveDate effective date
	 */
	public IdentificationNumberSummary(
			final Long id,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final String offenderSuffix,
			final Integer offenderNumber,
			final String issuerName,
			final String categoryName,
			final String value,
			final Date issueDate,
			final Date expireDate,
			final Date effectiveDate) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.issuerName = issuerName;
		this.categoryName = categoryName;
		this.value = value;
		this.issueDate = issueDate;
		this.expireDate = expireDate;
		this.active = new DateRange(issueDate, expireDate)
				.isActive(effectiveDate);
	}
	
	/**
	 * @param id - Long
	 * @param issuerName - String
	 * @param categoryName - String
	 * @param value - String
	 */
	public IdentificationNumberSummary(
			final Long id,
			final String issuerName,
			final String categoryName,
			final String value) {
		this.id = id;
		this.issuerName = issuerName;
		this.categoryName = categoryName;
		this.value = value;
		this.issueDate = null;
		this.expireDate = null;
		this.offenderLastName = null;
		this.offenderFirstName = null;
		this.offenderMiddleName = null;
		this.offenderSuffix = null;
		this.offenderNumber = null;
		this.active = null;
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns last name of offender.
	 * 
	 * @return last name of offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns first name of offender.
	 * 
	 * @return first name of offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns middle name of offender.
	 * 
	 * @return middle name of offender
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns suffix of offender.
	 * 
	 * @return suffix of offender
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
	
	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns name of issuer.
	 * 
	 * @return name of issuer
	 */
	public String getIssuerName() {
		return this.issuerName;
	}
	
	/**
	 * Returns name of category.
	 * 
	 * @return name of category
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Returns issue date.
	 * 
	 * @return issue date
	 */
	public Date getIssueDate() {
		return this.issueDate;
	}
	
	/**
	 * Returns expire date.
	 * 
	 * @return expire date
	 */
	public Date getExpireDate() {
		return this.expireDate;
	}
	
	/**
	 * Returns whether active.
	 * 
	 * @return whether active
	 */
	public Boolean getActive() {
		return this.active;
	}
}