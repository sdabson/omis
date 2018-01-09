package omis.separationneed.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Separation need removal.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 4, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedRemoval implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date date;
	
	private String comment;
	
	private SeparationNeedRemovalReason reason;
	
	/**
	 * Instantiates a default instance of separation need removal.
	 */
	public SeparationNeedRemoval() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a separation need removal with the specified properties.
	 * 
	 * @param date date
	 * @param comment comment
	 * @param reason reason
	 */
	public SeparationNeedRemoval(final Date date, 
			final String comment, final SeparationNeedRemovalReason reason) {
		this.date = date;
		this.comment = comment;
		this.reason = reason;
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
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * Returns the separation need removal reason.
	 * 
	 * @return separation need removal reason
	 */
	public SeparationNeedRemovalReason getReason() {
		return this.reason;
	}

	/**
	 * Sets the separation need removal reason.
	 * 
	 * @param reason separation need removal reason
	 */
	public void setReason(final SeparationNeedRemovalReason reason) {
		this.reason = reason;
	}
}