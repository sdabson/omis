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
 * Officer case assignment transfer form.
 * 
 * @author Josh Divine
 * @version 0.1.1 (Aug 13, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentTransferForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserAccount userAccountFrom;
	
	private UserAccount userAccountTo;
	
	private Date date;
	
	private Boolean swapCaseloads;
	
	/** 
	 * Instantiates a default instance of officer case assignment transfer form.
	 */
	public OfficerCaseAssignmentTransferForm() {
		//Default constructor.
	}

	/**
	 * Returns the user account to transfer from.
	 *
	 * @return user account to transfer from
	 */
	public UserAccount getUserAccountFrom() {
		return userAccountFrom;
	}

	/**
	 * Sets the user account to transfer from.
	 *
	 * @param userAccountFrom user account to transfer from
	 */
	public void setUserAccountFrom(final UserAccount userAccountFrom) {
		this.userAccountFrom = userAccountFrom;
	}

	/**
	 * Returns the user account to transfer to.
	 *
	 * @return user account to transfer to
	 */
	public UserAccount getUserAccountTo() {
		return userAccountTo;
	}

	/**
	 * Sets the user account to transfer to.
	 *
	 * @param userAccountTo user account to transfer to
	 */
	public void setUserAccountTo(final UserAccount userAccountTo) {
		this.userAccountTo = userAccountTo;
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
	 * Returns whether to swap the caseloads.
	 *
	 * @return whether to swap the caseloads
	 */
	public Boolean getSwapCaseloads() {
		return swapCaseloads;
	}

	/**
	 * Sets whether to swap the caseloads.
	 *
	 * @param swapCaseloads swap caseloads
	 */
	public void setSwapCaseloads(final Boolean swapCaseloads) {
		this.swapCaseloads = swapCaseloads;
	}
}