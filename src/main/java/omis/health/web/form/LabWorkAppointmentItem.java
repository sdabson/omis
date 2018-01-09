package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;

/**
 * Form item for lab work appointments.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (Apr 15, 2014)
 * @since OMIS 3.0
 */
public class LabWorkAppointmentItem
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LabWork labWork;
	
	private LabWorkRequirementRequest labWorkRequirementRequest;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date timeKept;
	
	private Date date;
	
	private Date orderDate;
	
	private String schedulingNotes;
	
	private String sampleNotes;

	private Lab sampleLab;
	
	private Lab resultsLab;
	
	private Date resultsDate;
	
	private String resultsNotes;
	
	private Boolean nothingPerOral;
	
	private Boolean noLeaky;
	
	private Boolean noMeds;
	
	private Boolean sampleTaken;
	
	private LabWorkCategory labWorkCategory;
	
	private ProviderAssignment byProvider;
	
	private Boolean process;
	
	/**
	 * Instantiates a form item for lab work appointments.
	 */
	public LabWorkAppointmentItem() {
		// Default instantiation
	}

	/**
	 * Returns the lab work.
	 * 
	 * @return lab work
	 */
	public LabWork getLabWork() {
		return this.labWork;
	}

	/**
	 * Sets the lab work.
	 * 
	 * @param labWork lab work
	 */
	public void setLabWork(final LabWork labWork) {
		this.labWork = labWork;
	}

	/**
	 * Returns the lab work requirement request.
	 * 
	 * @return lab work requirement request
	 */
	public LabWorkRequirementRequest getLabWorkRequirementRequest() {
		return this.labWorkRequirementRequest;
	}

	/**
	 * Sets the lab work requirement request.
	 * 
	 * @param labWorkRequirementRequest lab work requirement request
	 */
	public void setLabWorkRequirementRequest(
			final LabWorkRequirementRequest labWorkRequirementRequest) {
		this.labWorkRequirementRequest = labWorkRequirementRequest;
	}

	/**
	 * Returns the start time.
	 * 
	 * @return start time
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime start time
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Returns the end time.
	 * 
	 * @return end time
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets the end time.
	 * 
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Returns the time kept.
	 * 
	 * @return time kept
	 */
	public Date getTimeKept() {
		return this.timeKept;
	}

	/**
	 * Sets the time kept.
	 * 
	 * @param timeKept time kept
	 */
	public void setTimeKept(final Date timeKept) {
		this.timeKept = timeKept;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the order date.
	 * 
	 * @return order date
	 */
	public Date getOrderDate() {
		return this.orderDate;
	}

	/**
	 * Sets the order date.
	 * 
	 * @param orderDate order date
	 */
	public void setOrderDate(final Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Returns the scheduling notes.
	 * 
	 * @return scheduling notes
	 */
	public String getSchedulingNotes() {
		return this.schedulingNotes;
	}

	/**
	 * Sets the scheduling notes.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	public void setSchedulingNotes(final String schedulingNotes) {
		this.schedulingNotes = schedulingNotes;
	}

	/**
	 * Returns the sample notes.
	 * 
	 * @return sample notes
	 */
	public String getSampleNotes() {
		return this.sampleNotes;
	}

	/**
	 * Sets the sample notes.
	 * 
	 * @param sampleNotes sample notes
	 */
	public void setSampleNotes(final String sampleNotes) {
		this.sampleNotes = sampleNotes;
	}

	/**
	 * Returns the sample lab.
	 * 
	 * @return sample lab
	 */
	public Lab getSampleLab() {
		return this.sampleLab;
	}

	/**
	 * Sets the sample lab.
	 * 
	 * @param sampleLab sample lab
	 */
	public void setSampleLab(final Lab sampleLab) {
		this.sampleLab = sampleLab;
	}

	/**
	 * Returns the results lab.
	 * 
	 * @return results lab
	 */
	public Lab getResultsLab() {
		return this.resultsLab;
	}

	/**
	 * Sets the results lab.
	 * 
	 * @param resultsLab results lab
	 */
	public void setResultsLab(final Lab resultsLab) {
		this.resultsLab = resultsLab;
	}
	
	/**
	 * Returns the results date.
	 * 
	 * @return results date
	 */
	public Date getResultsDate() {
		return this.resultsDate;
	}

	/**
	 * Sets the results date.
	 * 
	 * @param resultsDate results date
	 */
	public void setResultsDate(final Date resultsDate) {
		this.resultsDate = resultsDate;
	}

	/**
	 * Returns the results notes.
	 * 
	 * @return results notes
	 */
	public String getResultsNotes() {
		return this.resultsNotes;
	}

	/**
	 * Sets the results notes.
	 * 
	 * @param resultsNotes results
	 */
	public void setResultsNotes(final String resultsNotes) {
		this.resultsNotes = resultsNotes;
	}

	/**
	 * Returns whether nothing per oral applies.
	 * 
	 * @return nothing per oral applies
	 */
	public Boolean getNothingPerOral() {
		return this.nothingPerOral;
	}

	/**
	 * Sets whether nothing per oral applies.
	 * 
	 * @param nothingPerOral nothing per oral applies
	 */
	public void setNothingPerOral(final Boolean nothingPerOral) {
		this.nothingPerOral = nothingPerOral;
	}

	/**
	 * Returns whether no leaky applies.
	 * 
	 * @return no leaky applies
	 */
	public Boolean getNoLeaky() {
		return this.noLeaky;
	}

	/**
	 * Sets whether no leaky applies.
	 * 
	 * @param noLeaky no leaky applies
	 */
	public void setNoLeaky(final Boolean noLeaky) {
		this.noLeaky = noLeaky;
	}

	/**
	 * Returns whether no medications applies.
	 * 
	 * @return whether no medications applies
	 */
	public Boolean getNoMeds() {
		return this.noMeds;
	}

	/**
	 * Sets whether no medications applies.
	 * 
	 * @param noMeds no medications applies
	 */
	public void setNoMeds(final Boolean noMeds) {
		this.noMeds = noMeds;
	}

	/**
	 * Returns the process value.
	 * 
	 * @return process
	 */
	public Boolean getProcess() {
		return this.process;
	}

	/**
	 * Sets the process value.
	 * 
	 * @param process process
	 */
	public void setProcess(final Boolean process) {
		this.process = process;
	}

	/**
	 * Returns the provider that ordered the lab work.
	 * 
	 * @return by provider
	 */
	public ProviderAssignment getByProvider() {
		return this.byProvider;
	}

	/**
	 * Sets the provider that ordered the lab work.
	 * 
	 * @param byProvider by provider
	 */
	public void setByProvider(final ProviderAssignment byProvider) {
		this.byProvider = byProvider;
	}

	/**
	 * Returns the lab work category.
	 * 
	 * @return lab work category
	 */
	public LabWorkCategory getLabWorkCategory() {
		return this.labWorkCategory;
	}

	/**
	 * Sets the lab work category.
	 * 
	 * @param labWorkCategory lab work category
	 */
	public void setLabWorkCategory(final LabWorkCategory labWorkCategory) {
		this.labWorkCategory = labWorkCategory;
	}

	/**
	 * Returns whether the sample has been taken.
	 * 
	 * @return sample has been taken
	 */
	public Boolean getSampleTaken() {
		return this.sampleTaken;
	}

	/**
	 * Sets whether the sample has been taken.
	 * 
	 * @param sampleTaken sample has been taken
	 */
	public void setSampleTaken(final Boolean sampleTaken) {
		this.sampleTaken = sampleTaken;
	}
}