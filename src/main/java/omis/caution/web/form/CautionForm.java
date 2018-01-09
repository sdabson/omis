/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.caution.web.form;

import java.util.Date;

import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;

/**
 * Used to capture caution information.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 23, 2013)
 * @since OMIS 3.0
 */
public class CautionForm {

	private CautionSource source;
	
	private String sourceComment;
	
	private CautionDescription description;
	
	private String comment;
	
	private Date startDate;
	
	private Date endDate;
	
	/** Instantiates a default caution form. */
	public CautionForm() {
		// Default instantiation
	}

	/**
	 * Returns the caution source.
	 * 
	 * @return caution source
	 */
	public CautionSource getSource() {
		return this.source;
	}

	/**
	 * Sets the caution source.
	 * 
	 * @param source caution source
	 */
	public void setSource(final CautionSource source) {
		this.source = source;
	}

	/**
	 * Returns the caution source comment.
	 * 
	 * @return caution source comment
	 */
	public String getSourceComment() {
		return this.sourceComment;
	}

	/**
	 * Sets the caution source comment.
	 * 
	 * @param sourceComment caution source comment
	 */
	public void setSourceComment(final String sourceComment) {
		this.sourceComment = sourceComment;
	}

	/**
	 * Returns the caution description.
	 * 
	 * @return caution description
	 */
	public CautionDescription getDescription() {
		return this.description;
	}

	/**
	 * Sets the caution description.
	 * 
	 * @param description caution description
	 */
	public void setDescription(final CautionDescription description) {
		this.description = description;
	}
	
	/**
	 * Returns the caution comment.
	 * 
	 * @return caution comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the caution comment.
	 * 
	 * @param comment caution comment
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