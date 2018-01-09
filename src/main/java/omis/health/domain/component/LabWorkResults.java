package omis.health.domain.component;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.Lab;

/**
 * Results of a lab work appointment.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 13, 2014)
 * @since OMIS 3.0
 */
public class LabWorkResults
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lab lab;
	
	private Date date;
	
	private String notes;
	
	/** Instantiates a the results of a lab work appointment. */
	public LabWorkResults() {
		// Default instantiation
	}

	/**
	 * Instantiates a the results of a lab work appointment.
	 * 
	 * @param lab lab
	 * @param date date
	 * @param notes notes
	 */
	public LabWorkResults(final Lab lab, final Date date, final String notes) {
		this.lab = lab;
		this.date = date;
		this.notes = notes;
	}
	
	/**
	 * Sets the lab.
	 * 
	 * @param lab lab
	 */
	public void setLab(final Lab lab) {
		this.lab = lab;
	}
	
	/**
	 * Returns the lab.
	 * 
	 * @return lab
	 */
	public Lab getLab() {
		return this.lab;
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
	 * Returns the date.
	 * 
	 * @return date
	 * @throws ParseException 
	 */
	public Date getDate() {
			return this.date;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
	
	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}
}