package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.ProviderAssignment;

public class LabWorkItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean process;
		
	private Date sampleDate;
	
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

	/**
	 * Instantiates a default instance of lab work item.
	 */
	public LabWorkItem() {
		//Default constructor.
	}
	
	/**
	 * Returns whether to process.
	 * 
	 * @return process
	 */
	public Boolean getProcess() {
		return this.process;
	}

	/**
	 * Sets whether to process.
	 * 
	 * @param process process
	 */
	public void setProcess(final Boolean process) {
		this.process = process;
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
	 * @param resultsLab
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
	 * @param resultsNotes results notes
	 */
	public void setResultsNotes(final String resultsNotes) {
		this.resultsNotes = resultsNotes;
	}

	/**
	 * Returns whether the nothing per oral restriction should be enforced.
	 * 
	 * @return nothing per oral restriction
	 */
	public Boolean getNothingPerOral() {
		return this.nothingPerOral;
	}

	/**
	 * Sets whether the nothing per oral restriction should be enforced.
	 * 
	 * @param nothingPerOral nothing per oral restriction
	 */
	public void setNothingPerOral(final Boolean nothingPerOral) {
		this.nothingPerOral = nothingPerOral;
	}

	/**
	 * Returns whether the no leaky restriction should be enforced.
	 * 
	 * @return no leaky restriction
	 */
	public Boolean getNoLeaky() {
		return this.noLeaky;
	}

	/**
	 * Sets whether the no leaky restriction should be enforced.
	 * 
	 * @param noLeaky no leaky restriction
	 */
	public void setNoLeaky(final Boolean noLeaky) {
		this.noLeaky = noLeaky;
	}

	/**
	 * Returns whether the no meds restriction should be enforced.
	 * 
	 * @return no meds restriction
	 */
	public Boolean getNoMeds() {
		return this.noMeds;
	}

	/**
	 * Sets whether the no meds restriciton should be enforced.
	 * 
	 * @param noMeds no meds restriction
	 */
	public void setNoMeds(final Boolean noMeds) {
		this.noMeds = noMeds;
	}

	/**
	 * Returns whether the sample has been taken.
	 * 
	 * @return sample taken
	 */
	public Boolean getSampleTaken() {
		return this.sampleTaken;
	}

	/**
	 * Sets whether the sample has been taken.
	 * 
	 * @param sampleTaken sample taken
	 */
	public void setSampleTaken(final Boolean sampleTaken) {
		this.sampleTaken = sampleTaken;
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
	 * Returns the provider assignment that ordered the lab work.
	 * 
	 * @return by provider
	 */
	public ProviderAssignment getByProvider() {
		return this.byProvider;
	}

	/**
	 * Sets the provider assignment that ordered the lab work.
	 * 
	 * @param byProvider by provider
	 */
	public void setByProvider(final ProviderAssignment byProvider) {
		this.byProvider = byProvider;
	}
}