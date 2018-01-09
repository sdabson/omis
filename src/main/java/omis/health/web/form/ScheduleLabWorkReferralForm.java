package omis.health.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.health.domain.LabWorkReferralStatusReason;
import omis.offender.domain.Offender;

/**
 * Schedule lab work referral form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0
 */
public class ScheduleLabWorkReferralForm {

	private Offender offender;
	
	private LabWorkReferralStatusReason statusReason;
	
	private String schedulingNotes;
	
	private Date sampleDate;
	
	private boolean offenderRequired;
	
	private List <LabWorkSampleItem> labWorkSampleItems =
			new ArrayList<LabWorkSampleItem>();
	
	/**
	 * Instantiates a default instance of lab form.
	 */
	public ScheduleLabWorkReferralForm() {
		//Default constructor.
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
	 * Returns the lab work referral status reason.
	 * 
	 * @return lab work referral status reason 
	 */
	public LabWorkReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the lab work referral status reason.
	 * 
	 * @param statusReason lab work referral status reason
	 */
	public void setStatusReason(
			final LabWorkReferralStatusReason statusReason) {
		this.statusReason = statusReason;
	}

	/**
	 * Returns the scheduling notes.
	 * 
	 * @return scheduling notes
	 */
	public String getSchedulingNotes() {
		return schedulingNotes;
	}

	/**
	 * Sets the scheduling notes.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	public void setSchedulingNotes(String schedulingNotes) {
		this.schedulingNotes = schedulingNotes;
	}

	/**
	 * Returns the sample date.
	 * 
	 * @return sample date
	 */
	public Date getSampleDate() {
		return this.sampleDate;
	}

	/**
	 * Sets the sample date.
	 * 
	 * @param sampleDate sample date
	 */
	public void setSampleDate(final Date sampleDate) {
		this.sampleDate = sampleDate;
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
	 * Returns the lab work sample items.
	 * 
	 * @return lab work sample items
	 */
	public List<LabWorkSampleItem> getLabWorkSampleItems() {
		return this.labWorkSampleItems;
	}

	/**
	 * Sets the lab work sample items.
	 * 
	 * @param labWorkSampleItems lab work sample items
	 */
	public void setLabWorkSampleItems(
			final List<LabWorkSampleItem> labWorkSampleItems) {
		this.labWorkSampleItems = labWorkSampleItems;
	}
}