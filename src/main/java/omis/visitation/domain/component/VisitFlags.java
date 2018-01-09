package omis.visitation.domain.component;

import java.io.Serializable;

import omis.visitation.domain.VisitMethod;


/**
 * Visit flags.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 23, 2016)
 * @since OMIS 3.0
 */
public class VisitFlags implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean deniedByStaff;
	
	private Boolean refusedByOffender;
	
	private VisitMethod method;
	
	/**
	 * Instantiates a default instance of visit flags.
	 */
	public VisitFlags() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of visit flags with the specified values.
	 * 
	 * @param deniedByStaff denied by staff
	 * @param refusedByOffender refused by offender
	 * @param visitMethod visit method
	 */
	public VisitFlags(final Boolean deniedByStaff,
			final Boolean refusedByOffender, VisitMethod method) {
		this.deniedByStaff = deniedByStaff;
		this.refusedByOffender = refusedByOffender;
		this.method = method;
	}

	/**
	 * Returns whether denied by staff applies.
	 * 
	 * @return denied by staff
	 */
	public Boolean getDeniedByStaff() {
		return this.deniedByStaff;
	}

	/**
	 * Sets whether denied by staff applies.
	 * 
	 * @param deniedByStaff denied by staff
	 */
	public void setDeniedByStaff(final Boolean deniedByStaff) {
		this.deniedByStaff = deniedByStaff;
	}

	/**
	 * Returns whether refused by offender applies.
	 * 
	 * @return refused by offender
	 */
	public Boolean getRefusedByOffender() {
		return this.refusedByOffender;
	}

	/**
	 * Sets whether refused by offender applies.
	 * 
	 * @param refusedByOffender refused by offender
	 */
	public void setRefusedByOffender(final Boolean refusedByOffender) {
		this.refusedByOffender = refusedByOffender;
	}

	/**
	 * Returns the visit method.
	 * 
	 * @return visit method
	 */
	public VisitMethod getMethod() {
		return this.method;
	}

	/**
	 * Sets the visit method.
	 * 
	 * @param method visit method
	 */
	public void setMethod(VisitMethod method) {
		this.method = method;
	}
}