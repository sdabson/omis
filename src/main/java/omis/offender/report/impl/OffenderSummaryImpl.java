package omis.offender.report.impl;

import java.util.Date;

import omis.demographics.domain.Sex;
import omis.offender.report.OffenderSummary;

/**
 * Summary of offender information.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Jul 27, 2015)
 * @since OMIS 3.0
 */
public final class OffenderSummaryImpl
				implements OffenderSummary {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final Integer offenderNumber;
	
	private final String lastName;
	
	private final String firstName;
	
	private final String middleName;
	
	private final String suffix;
	
	private final Date birthDate;
	
	private final Sex sex;
	
	private final Integer socialSecurityNumber;

	/**
	 * Instantiates an offender summary.
	 * 
	 * @param id offender ID
	 * @param offenderNumber offender number
	 * @param lastName last name of offender
	 * @param firstName first name of offender
	 * @param middleName middle name of offender
	 * @param suffix suffix of offender
	 * @param birthDate birth date
	 * @param sex sex
	 */
	public OffenderSummaryImpl(final Long id, final Integer offenderNumber,
					final String lastName, final String firstName,
					final String middleName, final String suffix,
					final Date birthDate, final Sex sex, 
					final Integer socialSecurityNumber) {
		this.id = id;
		this.offenderNumber = offenderNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.birthDate = birthDate;
		this.sex = sex;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	/**
	 * Instantiates an offender summary without middle name or suffix or ssn.
	 * 
	 * @param id offender ID
	 * @param offenderNumber offender number
	 * @param lastName last name of offender
	 * @param firstName first name of offender
	 * @param birthDate birth date
	 * @param sex sex
	 */
	public OffenderSummaryImpl(final Long id, final Integer offenderNumber,
					final String lastName, final String firstName,
					final Date birthDate, final Sex sex) {
		this.id = id;
		this.offenderNumber = offenderNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = null;
		this.suffix = null;
		this.birthDate = birthDate;
		this.sex = sex;
		this.socialSecurityNumber = null;
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getLastName() {
		return this.lastName;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getFirstName() {
		return this.firstName;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getMiddleName() {
		return this.middleName;
	}

	/** {@inheritDoc} */
	@Override
	public String getSuffix() {
		return this.suffix;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getBirthDate() {
		return this.birthDate; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Sex getSex() {
		return this.sex; 
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSocialSecurityNumber() {
		return socialSecurityNumber;
	}	
	
	
}