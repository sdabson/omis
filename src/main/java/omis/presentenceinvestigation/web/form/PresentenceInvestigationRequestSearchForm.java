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
package omis.presentenceinvestigation.web.form;

import java.io.Serializable;
import java.util.Date;
import omis.user.domain.UserAccount;

/**
 * Presentence Investigation Request Search Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 28, 2019)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestSearchForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UserAccount userAccount;
	
	private Date startDate;
	
	private Date endDate;
	
	/**
	 * Default constructor for PresentenceInvestigationRequestForm.
	 */
	public PresentenceInvestigationRequestSearchForm() {
	}

	/**
	 * Returns the userAccount.
	 *
	 * @return userAccount
	 */
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/**
	 * Sets the userAccount.
	 *
	 * @param userAccount - userAccount
	 */
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * Returns the startDate.
	 *
	 * @return startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the startDate.
	 *
	 * @param startDate - startDate
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the endDate.
	 *
	 * @return endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the endDate.
	 *
	 * @param endDate - endDate
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}
