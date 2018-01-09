package omis.incident.report;

import java.io.Serializable;

/**
 * Involved party summary
 * @author Yidong 
 * @version 0.1.0 (July 17, 2015)
 * @since OMIS 3.0
 */

public class InvolvedPartySummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final Boolean offender; 
	private final String firstName;
	private final String lastName;
	private final Integer offenderNumber;
	
	/**
	 * Instantiates a default instance of the incident summary.
	 */
	public InvolvedPartySummary(
			final Long id,
			final Boolean offender,
			final String firstName,
			final String lastName,
			final Integer offenderNumber
			){
		this.id = id;
		this.offender = offender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.offenderNumber = offenderNumber;
	}
	
	/**
	 * Return the incident report Id.
	 * 
	 * @return the incident report Id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Return offender.
	 * 
	 * @return offender
	 */
	public Boolean getOffender() {
		return this.offender;
	}
	
	/**
	 * Return the offender number.
	 * 
	 * @return the offender number
	 */

	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Return the first name.
	 * 
	 * @return the first name
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Return the last name.
	 * 
	 * @return the last name
	 */
	
	public String getLastName() {
		return this.lastName;
	}

}
