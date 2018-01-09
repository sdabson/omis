package omis.alert.domain.component;

import java.io.Serializable;
import java.util.Date;

import omis.person.domain.Person;

/**
 * Alert resolution.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 18, 2014)
 * @since OMIS 3.0
 */
public class AlertResolution
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String description;
	
	private Long date;
	
	private Person resolvedBy;
	
	/** Instantiates an alert resolution. */
	public AlertResolution() {
		// Default instantiation
	}
	
	/**
	 * Instantiates an alert resolution.
	 * 
	 * @param description description
	 * @param date date
	 * @param resolvedBy person that resolved alert
	 */
	public AlertResolution(final String description,
			final Date date, final Person resolvedBy) {
		this.description = description;
		if (date != null) {
			this.date = date.getTime();
		} else {
			this.date = null;
		}
		this.resolvedBy = resolvedBy;
	}
	
	/**
	 * Sets the description.
	 * 
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		if (date != null) {
			this.date = date.getTime();
		} else {
			this.date = null;
		}
	}
	
	/**
	 * Returns the date.
	 *
	 * @return date
	 */
	public Date getDate() {
		if (this.date != null) {
			return new Date(this.date);
		} else {
			return null;
		}
	}

	/**
	 * Sets the person that resolved the alert.
	 * 
	 * @param resolvedBy person that resolved alert
	 */
	public void setResolvedBy(final Person resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	
	/**
	 * Returns the person that resolved the alert.
	 * 
	 * @return person that resolved alert
	 */
	public Person getResolvedBy() {
		return this.resolvedBy;
	}
}