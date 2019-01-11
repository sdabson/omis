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
package omis.caseload.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Officer case assignment search form.
 * 
 * @author Josh Divine
 * @version 0.1.1 (Sep 11, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserAccount user;
	
	private Boolean singleDate;
	
	private Date date;
	
	private Date startDate;
	
	private Date endDate;

	private Boolean allowInterstateCompact;
	
	/** 
	 * Instantiates a default instance of officer case assignment search form.
	 */
	public OfficerCaseAssignmentSearchForm() {
		//Default constructor.
	}

	/**
	 * Returns the user account.
	 *
	 * @return user account
	 */
	public UserAccount getUser() {
		return user;
	}

	/**
	 * Sets the user account.
	 *
	 * @param userAccount user account
	 */
	public void setUser(final UserAccount userAccount) {
		this.user = userAccount;
	}

	/**
	 * Returns the single date.
	 *
	 * @return single date
	 */
	public Boolean getSingleDate() {
		return singleDate;
	}

	/**
	 * Sets the single date.
	 *
	 * @param singleDate single date
	 */
	public void setSingleDate(final Boolean singleDate) {
		this.singleDate = singleDate;
	}

	/**
	 * Returns the date.
	 *
	 * @return date
	 */
	public Date getDate() {
		return date;
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
	 * Returns the start date.
	 *
	 * @return start date
	 */
	public Date getStartDate() {
		return startDate;
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
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns whether to allow interstate compact.
	 *
	 * @return whether to allow interstate compact
	 */
	public Boolean getAllowInterstateCompact() {
		return allowInterstateCompact;
	}

	/**
	 * Sets whether to allow interstate compact.
	 *
	 * @param allowInterstateCompact whether to allow interstate compact
	 */
	public void setAllowInterstateCompact(Boolean allowInterstateCompact) {
		this.allowInterstateCompact = allowInterstateCompact;
	}
}