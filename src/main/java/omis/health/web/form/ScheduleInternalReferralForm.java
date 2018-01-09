package omis.health.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.health.domain.InternalReferralReason;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.offender.domain.Offender;

/**
 * Schedule request form.
 *
 * @author Ryan Johns
 * @author Stephen Abson
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 15, 2014)
 * @since OMIS 3.0
 */
public class ScheduleInternalReferralForm {

	private ProviderAssignment providerAssignment;

	private Date scheduleDate;

	private String notes;

	private List<LabWorkAppointmentItem> labWork
		= new ArrayList<LabWorkAppointmentItem>();
	
	private InternalReferralReason reason;
	
	private ProviderLevel providerLevel;

	private boolean offenderRequired;
	
	private Offender offender;
	
	private ReferralLocationDesignator locationDesignator;

	private boolean statusReasonRequired;
	
	private InternalReferralStatusReason statusReason;

	/**
	 * Instantiates a default instance of schedule request form.
	 */
	public ScheduleInternalReferralForm() {
		//Default constructor
	}

	/** Gets provider.
	 * @return provider */
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}

	/** Gets schedule date.
	 * @return schedule date */
	public Date getScheduleDate() {
		return this.scheduleDate;
	}

	/** Gets notes.
	 * @return notes */
	public String getNotes() {
		return this.notes;
	}

	/** Gets lab work.
	 * @return lab work */
	public List<LabWorkAppointmentItem> getLabWork() {
		return this.labWork;
	}

	/** Sets provider assignment.
	 * @param providerAssignment provider assignment */
	public void setProviderAssignment(
			final ProviderAssignment providerAssignment) {
		this.providerAssignment = providerAssignment;
	}

	/** Sets schedule date.
	 * @param scheduleDate schedule date */
	public void setScheduleDate(final Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/** Sets notes.
	 * @param notes notes */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/** Sets lab work.
	 * @param labWork lab work */
	public void setLabWork(final List<LabWorkAppointmentItem> labWork) {
		this.labWork = labWork;
	}
	
	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public InternalReferralReason getReason() {
		return this.reason;
	}

	/**
	 * Sets the reason.
	 * 
	 * @param reason reason
	 */
	public void setReason(final InternalReferralReason reason) {
		this.reason = reason;
	}
	
	/**
	 * Returns the provider level.
	 * 
	 * @return provider level
	 */
	public ProviderLevel getProviderLevel() {
		return this.providerLevel;
	}

	/**
	 * Sets the provider level.
	 * 
	 * @param providerLevel provider level
	 */
	public void setProviderLevel(final ProviderLevel providerLevel) {
		this.providerLevel = providerLevel;
	}
	
	/**
	 * Returns the health appointment designation.
	 * 
	 * @return referral location designator
	 */
	public ReferralLocationDesignator getLocationDesignator(){
		return this.locationDesignator;
	}
	 /**
	  * Sets the health appointment designation.
	  * 
	  * @param location designator
	  */
	public void setLocationDesignator(
			final ReferralLocationDesignator locationDesignator){
		this.locationDesignator = locationDesignator;
	}

	/**
	 * Returns whether an offender is required.
	 * 
	 * @return whether offender is required
	 */
	public boolean getOffenderRequired() {
		return this.offenderRequired;
	}

	/**
	 * Sets whether and offender is required.
	 * 
	 * @param offenderRequired whether offender is required
	 */
	public void setOffenderRequired(final boolean offenderRequired) {
		this.offenderRequired = offenderRequired;
	}
	
	/**
	 * Returns the offender.
	 *
	 * @return offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Sets the offender.
	 *
	 * @param offender offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**
	 * Returns whether the status reason is required.
	 * 
	 * @return whether status reason is required
	 */
	public boolean getStatusReasonRequired() {
		return this.statusReasonRequired;
	}

	/**
	 * Sets whether status reason is required.
	 * 
	 * @param statusReasonRequired whether status reason is required
	 */
	public void setStatusReasonRequired(
			final boolean statusReasonRequired) {
		this.statusReasonRequired = statusReasonRequired;
	}

	/**
	 * Returns the status reason.
	 * 
	 * @return status reason
	 */
	public InternalReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason status reason
	 */
	public void setStatusReason(
			final InternalReferralStatusReason statusReason) {
		this.statusReason = statusReason;
	}
}