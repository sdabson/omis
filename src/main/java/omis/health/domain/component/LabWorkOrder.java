package omis.health.domain.component;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ProviderAssignment;

/**
 * Order for a lab work appointment.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 13, 2014)
 * @since OMIS 3.0
 */
public class LabWorkOrder
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProviderAssignment byAssignment;
	
	private Date date;
	
	/** Instantiates a lab work order. */
	public LabWorkOrder() {
		// Default instantiation
	}

	/**
	 * Instantiates a lab work order.
	 * 
	 * @param byAssignment assignment of provider ordering lab work 
	 * @param date date ordered
	 */
	public LabWorkOrder(
			final ProviderAssignment byAssignment, final Date date) {
		this.byAssignment = byAssignment;
		this.date = date;
	}
	
	/**
	 * Sets the assignment of the provider ordering the lab work appointment.
	 * 
	 * @param byAssignment assignment of provider ordering lab work
	 */
	public void setByAssignment(
			final ProviderAssignment byAssignment) {
		this.byAssignment = byAssignment;
	}

	/**
	 * Returns the assignment of the provider ordering the lab work appointment.
	 * 
	 * @return assignment of provider ordering lab work
	 */
	public ProviderAssignment getByAssignment() {
		return this.byAssignment;
	}
	
	/**
	 * Sets the date ordered.
	 * 
	 * @param date date ordered
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * Returns the date ordered.
	 * 
	 * @return date ordered
	 */
	public Date getDate() {
		return this.date;
	}
}