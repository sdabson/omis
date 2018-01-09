package omis.tierdesignation.web.form;

import java.util.Date;

import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;

/**
 * Form for tier designations.
 * 
 * @author Jason Nelson
 * @version 0.1.1 (Dec 13, 2013)
 * @since OMIS 3.0
 */
public class TierDesignationForm {
	
	private TierLevel level;
	
	private TierSource source;
	
	private TierChangeReason changeReason;
	
	private Date startDate;
	
	private Date endDate;
	
	private String comment;
	
	/** Instantiates a form for tier designations. */
	public TierDesignationForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the level.
	 * 
	 * @return level
	 */
	public TierLevel getLevel() {
		return this.level;
	}
	
	/**
	 * Sets the level.
	 * 
	 * @param level level
	 */
	public void setLevel(final TierLevel level) {
		this.level = level;
	}
	
	/**
	 * Returns the source.
	 * 
	 * @return source
	 */
	public TierSource getSource() {
		return this.source;
	}
	
	/**
	 * Sets the source.
	 * 
	 * @param source source
	 */
	public void setSource(final TierSource source) {
		this.source = source;
	}
	
	/**
	 * Returns the change reason.
	 * 
	 * @return change reason
	 */
	public TierChangeReason getChangeReason() {
		return this.changeReason;
	}
	
	/**
	 * Sets the change reason.
	 * 
	 * @param changeReason change reason
	 */
	public void setChangeReason(final TierChangeReason changeReason) {
		this.changeReason = changeReason;
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
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}