package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;

/**
 * Lab work results item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 3, 2014)
 * @since OMIS 3.0
 */
public class LabWorkResultsItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean process;

	private Lab resultsLab;
	
	private LabWorkCategory labWorkCategory;
	
	private Date resultsDate;
	
	private String resultsNotes;
	
	/**
	 * Instantiates a default instance of lab work results item. 
	 */
	public LabWorkResultsItem() {
		//Default constructor.
	}

	/**
	 * Returns whether to process the lab work result item.
	 * 
	 * @return process
	 */
	public Boolean getProcess() {
		return this.process;
	}

	/**
	 * Sets whether to process the lab work result item.
	 * 
	 * @param process process
	 */
	public void setProcess(final Boolean process) {
		this.process = process;
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
}