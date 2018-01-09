package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.HealthAppointmentStatus;
import omis.offender.domain.Offender;

/**
 * Form to filter referral center.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 19, 2014)
 * @since OMIS 3.0
 */
public class ReferralCenterFilterForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Offender filterByOffender;
	
	private Date filterByStartDate;
	
	private Date filterByEndDate;
	
	private ReferralType filterByReferralType;
	
	private HealthAppointmentStatus filterByAppointmentStatus;
	
	/** Instantiates form to filter referral center. */
	public ReferralCenterFilterForm() {
		// Default instantiation
	}

	/**
	 * Returns the offender by which to filter.
	 * 
	 * @return offender by which to filter.
	 */
	public Offender getFilterByOffender() {
		return this.filterByOffender;
	}

	/**
	 * Sets the offender by which to filter.
	 * 
	 * @param filterByOffender offender by which to filter
	 */
	public void setFilterByOffender(final Offender filterByOffender) {
		this.filterByOffender = filterByOffender;
	}

	/**
	 * Returns the start date by which to filter.
	 * 
	 * @return start date by which to filter
	 */
	public Date getFilterByStartDate() {
		return this.filterByStartDate;
	}

	/**
	 * Sets the start date by which to filter.
	 * 
	 * @param filterByStartDate start date by which to filter
	 */
	public void setFilterByStartDate(final Date filterByStartDate) {
		this.filterByStartDate = filterByStartDate;
	}

	/**
	 * Returns the end date by which to filter.
	 * 
	 * @return end date by which to filter
	 */
	public Date getFilterByEndDate() {
		return this.filterByEndDate;
	}

	/**
	 * Sets the end date by which to filter.
	 * 
	 * @param filterByEndDate end date by which to filter
	 */
	public void setFilterByEndDate(final Date filterByEndDate) {
		this.filterByEndDate = filterByEndDate;
	}

	/**
	 * Returns the referral type by which to filter.
	 * 
	 * @return referral type by which to filter
	 */
	public ReferralType getFilterByReferralType() {
		return this.filterByReferralType;
	}

	/**
	 * Sets the referral type by which to filter.
	 * 
	 * @param filterByReferralType referral type by which to filter
	 */
	public void setFilterByReferralType(
			final ReferralType filterByReferralType) {
		this.filterByReferralType = filterByReferralType;
	}

	/**
	 * Returns the appointment status by which to filter.
	 * 
	 * @return appointment status by which to filter
	 */
	public HealthAppointmentStatus getFilterByAppointmentStatus() {
		return this.filterByAppointmentStatus;
	}

	/**
	 * Sets the appointment status by which to filter.
	 * 
	 * @param filterByAppointmentStatus appointment status by which to filter
	 */
	public void setFilterByAppointmentStatus(
			final HealthAppointmentStatus filterByAppointmentStatus) {
		this.filterByAppointmentStatus = filterByAppointmentStatus;
	}
}