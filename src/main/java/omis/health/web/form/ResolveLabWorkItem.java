package omis.health.web.form;

/**
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 18, 2014)
 * @since OMIS 3.0
 */

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;
import omis.health.domain.LabWork;

/**
 * Lab work resolution form.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 18, 2014)
 * @since OMIS 3.0
 */
public class ResolveLabWorkItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private ResolveLabWorkItemOperation operation;
	
	private LabWork labWork;
	
	private Boolean taken;
	
	private String sampleNotes;
	
	private Lab resultsLab;
	
	private Date resultsDate;
	
	private String resultNotes;
	
	/**
	 * Instantiates a default instance of the Lab work resolution item.
	 */
	public ResolveLabWorkItem() {
		//Default constructor.
	}
	
	/**
	 * Return the operation of the lab work resolution item.
	 * 
	 * @return the operation
	 */
	public ResolveLabWorkItemOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Set the operation of the lab work resolution item.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final ResolveLabWorkItemOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Return labWork.
	 * 
	 * @return LabWork lab Work
	 */
	public LabWork getLabWork() {
		return this.labWork;
	}
	
	/**
	 * Set labWork
	 * 
	 * @param labWork lab Work
	 */
	public void setLabWork(final LabWork labWork) {
		this.labWork = labWork;
	}	
	
	/**
	 * Return taken.
	 * 
	 * @return taken
	 */
	public Boolean getTaken() {
		return this.taken;
	}
	
	/**
	 * Set taken
	 * 
	 * @param Boolean taken
	 */
	public void setTaken(final Boolean taken) {
		this.taken = taken;
	}
	
	/**
	 * Return Sample_Notes.
	 * 
	 * @return Sample Notes
	 */
	public String getSampleNotes() {
		return this.sampleNotes;
	}
	
	/**
	 * Set Sample_Notes
	 * 
	 * @param sampleNotes sample notes
	 */
	public void setSampleNotes(final String sampleNotes) {
		this.sampleNotes = sampleNotes;
	}
	
	/**
	 * Return ResultsLab.
	 * 
	 * @return results Lab
	 */
	public Lab getResultsLab() {
		return this.resultsLab;
	}
	
	/**
	 * Set ResultsLab
	 * 
	 * @param resultsLab results lab
	 */
	public void setResultsLab(final Lab resultsLab) {
		this.resultsLab = resultsLab;
	}
	
	/**
	 * Return result notes.
	 * 
	 * @return resultNotes
	 */
	public String getResultNotes() {
		return this.resultNotes;
	}
	
	/**
	 * Set resultNote
	 * 
	 * @param resultsNotes results date
	 */
	public void setResultNotes(final String resultNotes) {
		this.resultNotes = resultNotes;
	}
	
	/**
	 * Return result date.
	 * 
	 * @return resultDate result date
	 */
	public Date getResultsDate() {
		return this.resultsDate;
	}
	
	/**
	 * Set result date
	 * 
	 * @param resultDate result date
	 */
	public void setResultsDate(final Date resultsDate) {
		this.resultsDate = resultsDate;
	}
}
