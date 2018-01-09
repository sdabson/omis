package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;

/**
 * Item to request lab work requirement.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementRequestItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LabWorkRequirementRequestOperation operation;
	
	private LabWorkRequirementRequest request;
	
	private ProviderAssignment sampleOrderedBy;
	
	private Date sampleOrderedDate;
	
	private LabWorkCategory category;
	
	private Date sampleDate;
	
	private Lab sampleLab;
	
	private Lab resultsLab;
	
	private Boolean nothingPerOralSampleRestriction;
	
	private Boolean noLeakySampleRestriction;
	
	private Boolean noMedsSampleRestriction;
	
	private String schedulingNotes;
	
	/** Instantiates item to request lab work requirement. */
	public LabWorkRequirementRequestItem() {
		// Default instantiation
	}

	/**
	 * Returns the operation.
	 * 
	 * @return operation
	 */
	public LabWorkRequirementRequestOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(
			final LabWorkRequirementRequestOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns the request.
	 * 
	 * @return request
	 */
	public LabWorkRequirementRequest getRequest() {
		return this.request;
	}

	/**
	 * Sets the request.
	 * 
	 * @param request request
	 */
	public void setRequest(final LabWorkRequirementRequest request) {
		this.request = request;
	}

	/**
	 * Returns the assignment of the provider that ordered the sample.
	 * 
	 * @return assignment of provider that ordered sample
	 */
	public ProviderAssignment getSampleOrderedBy() {
		return this.sampleOrderedBy;
	}

	/**
	 * Sets the assignment of the provider that ordered the sample.
	 * 
	 * @param sampleOrderedBy assignment of provider that ordered sample
	 */
	public void setSampleOrderedBy(
			final ProviderAssignment sampleOrderedBy) {
		this.sampleOrderedBy = sampleOrderedBy;
	}

	/**
	 * Returns the date the sample was ordered. 
	 * 
	 * @return date sample was ordered
	 */
	public Date getSampleOrderedDate() {
		return this.sampleOrderedDate;
	}

	/**
	 * Sets the date the sample was ordered.
	 * 
	 * @param sampleOrderedDate date sample was ordered
	 */
	public void setSampleOrderedDate(final Date sampleOrderedDate) {
		this.sampleOrderedDate = sampleOrderedDate;
	}

	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public LabWorkCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	public void setCategory(final LabWorkCategory category) {
		this.category = category;
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
	 * Returns whether nothing per oral restriction applies.
	 * 
	 * @return whether nothing per oral restriction applies
	 */
	public Boolean getNothingPerOralSampleRestriction() {
		return this.nothingPerOralSampleRestriction;
	}

	/**
	 * Sets whether nothing per oral restriction applies.
	 * 
	 * @param nothingPerOralSampleRestriction whether nothing per oral
	 * restriction applies
	 */
	public void setNothingPerOralSampleRestriction(
			final Boolean nothingPerOralSampleRestriction) {
		this.nothingPerOralSampleRestriction = nothingPerOralSampleRestriction;
	}

	/**
	 * Returns whether no leaky restriction applies.
	 * 
	 * @return whether no leaky restriction applies
	 */
	public Boolean getNoLeakySampleRestriction() {
		return this.noLeakySampleRestriction;
	}

	/**
	 * Sets whether no leaky restriction applies.
	 * 
	 * @param noLeakySampleRestriction whether no leaky restriction applies
	 */
	public void setNoLeakySampleRestriction(
			final Boolean noLeakySampleRestriction) {
		this.noLeakySampleRestriction = noLeakySampleRestriction;
	}
	
	/**
	 * Returns whether no medications restriction applies.
	 * 
	 * @return whether no medications restriction applies
	 */
	public Boolean getNoMedsSampleRestriction() {
		return this.noMedsSampleRestriction;
	}

	/**
	 * Sets whether no medications restriction applies.
	 * 
	 * @param noMedsSampleRestriction whether no medications restriction applies
	 */
	public void setNoMedsSampleRestriction(
			final Boolean noMedsSampleRestriction) {
		this.noMedsSampleRestriction = noMedsSampleRestriction;
	}

	/**
	 * Returns the scheduling notes.
	 * 
	 * @return notes
	 */
	public String getSchedulingNotes() {
		return this.schedulingNotes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param schedulingNotes notes
	 */
	public void setSchedulingNotes(final String schedulingNotes) {
		this.schedulingNotes = schedulingNotes;
	}
}