package omis.guardianship.web.form;

import omis.datatype.DateRange;
import omis.person.domain.Person;

/**
 * Guardianship form. 
 * @author Joel Norris
 * @version 0.1.0 (Sep 9, 2013)
 * @since OMIS 3.0
 */
public class GuardianshipForm {

	private Person guardian;
	
	private Person dependent;
	
	private DateRange dateRange;
	
	/**
	 * Instantiates a default instance of guardianship form.
	 */
	public GuardianshipForm() {
		//Default constructor.
	}

	/**
	 * Return the guardian of the guardianship form.
	 * @return guardian
	 */
	public Person getGuardian() {
		return this.guardian;
	}

	/**
	 * Set the guardian of the guardianship form.
	 * @param guardian guardian
	 */
	public void setGuardian(final Person guardian) {
		this.guardian = guardian;
	}

	/**
	 * Return the dependent of the guardianship form.
	 * @return dependent
	 */
	public Person getDependent() {
		return this.dependent;
	}

	/**
	 * Set the dependent of the guardianship form.
	 * @param dependent dependent
	 */
	public void setDependent(final Person dependent) {
		this.dependent = dependent;
	}

	/**
	 * Return the date range of the guardianship form.
	 * @return dateRange
	 */
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/**
	 * Set the date range of the guardianship form.
	 * @param dateRange date range
	 */
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}	
}