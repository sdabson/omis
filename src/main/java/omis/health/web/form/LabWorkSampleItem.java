package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.ProviderAssignment;

/**
 * Lab work sample item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 7, 2014)
 * @since OMIS 3.0
 */
public class LabWorkSampleItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean process;
	
	private Lab sampleLab;
	
	private String sampleNotes;
	
	private String schedulingNotes;
	
	private LabWorkCategory labWorkCategory;
	
	private LabWork labWork;
	
	private Boolean nothingPerOral;
	
	private Boolean noLeaky;
	
	private Boolean noMeds;
	
	private Boolean sampleTaken;
	
	private Date orderDate;
	
	private Date sampleDate;
	
	private ProviderAssignment byProvider;

	/**
	 * Instantiates a default lab work sample item.
	 */
	public LabWorkSampleItem() {
		//Default constructor.
	}
	
	/**
	 * Returns the process status.
	 * 
	 * @return process
	 */
	public Boolean getProcess() {
		return this.process;
	}

	/**
	 * Sets the process status.
	 * 
	 * @param process process
	 */
	public void setProcess(final Boolean process) {
		this.process = process;
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
	 * Returns the status of nothing per oral restriction.
	 * 
	 * @return nothing per oral restriction
	 */
	public Boolean getNothingPerOral() {
		return this.nothingPerOral;
	}

	/**
	 * Sets the status of nothing per oral restriction.
	 * 
	 * @param nothingPerOral nothing per oral
	 */
	public void setNothingPerOral(final Boolean nothingPerOral) {
		this.nothingPerOral = nothingPerOral;
	}

	/**
	 * Returns the status of no leaky restriction.
	 * 
	 * @return no leaky restriction
	 */
	public Boolean getNoLeaky() {
		return this.noLeaky;
	}

	/**
	 * Sets the status of no leaky restriction.
	 * 
	 * @param noLeaky no leaky
	 */
	public void setNoLeaky(final Boolean noLeaky) {
		this.noLeaky = noLeaky;
	}

	/**
	 * Returns the status of no meds restriction.
	 * 
	 * @return no meds restriction
	 */
	public Boolean getNoMeds() {
		return noMeds;
	}

	/**
	 * Sets the status of no meds restriction.
	 * 
	 * @param noMeds no meds
	 */
	public void setNoMeds(Boolean noMeds) {
		this.noMeds = noMeds;
	}

	/**
	 * Returns whether the sample has been taken or not.
	 * 
	 * @return sample taken
	 */
	public Boolean getSampleTaken() {
		return this.sampleTaken;
	}

	/**
	 * Sets whether the sample has been taken or not.
	 * 
	 * @param sampleTaken sample taken
	 */
	public void setSampleTaken(final Boolean sampleTaken) {
		this.sampleTaken = sampleTaken;
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
	 * Returns the provider responsible for the order.
	 * 
	 * @return by provider
	 */
	public ProviderAssignment getByProvider() {
		return this.byProvider;
	}

	/**
	 * Sets the provider responsible for the order.
	 * 
	 * @param byProvider by provider
	 */
	public void setByProvider(final ProviderAssignment byProvider) {
		this.byProvider = byProvider;
	}
}