package omis.visitation.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.person.domain.Person;

/**
 * Guardian visitor item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 3, 2015)
 * @since OMIS 3.0
 */
public class GuardianshipItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Person person;
	
	private Date startDate;
	
	private Date endDate;
	
	private GuardianshipItemOperation operation;

	/**
	 * Instantiates a default instance of guardian visitor item.
	 */
	public GuardianshipItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of guardianship item.
	 * 
	 * @param person person
	 * @param startDate start date
	 * @param endDate end date
	 * @param operation guardianship item operation
	 */
	public GuardianshipItem(final Person person, final Date startDate,
			final Date endDate, final GuardianshipItemOperation operation) {
		this.person = person;
		this.startDate = startDate;
		this.endDate = endDate;
		this.operation = operation;
	}

	/**
	 * Returns person.
	 * 
	 * @return person
	 */
	public Person getPerson() {
		return this.person;
	}

	/**
	 * Sets person.
	 * 
	 * @param person person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the guardianship item operation.
	 * 
	 * @return operation
	 */
	public GuardianshipItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the guardianship item operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final GuardianshipItemOperation operation) {
		this.operation = operation;
	}
}