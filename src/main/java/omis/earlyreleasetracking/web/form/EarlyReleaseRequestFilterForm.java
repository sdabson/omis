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
package omis.earlyreleasetracking.web.form;

import java.io.Serializable;
import java.util.Date;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;

/**
 * Early Release Request Filter Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 20, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestFilterForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date eligibilityDate;
	
	private Date eligibilityStartDate;
	
	private Date eligibilityEndDate;
	
	private EarlyReleaseStatusCategory releaseStatus;
	
	private Date requestDate;
	
	private Date requestStartDate;
	
	private Date requestEndDate;
	
	private Boolean singleEligibilityDate;
	
	private Boolean singleRequestDate;
	
	private Boolean noResponse;
	
	/**
	 * Default constructor for EarlyReleaseRequestFilterForm.
	 */
	public EarlyReleaseRequestFilterForm() {
	}

	/**
	 * Returns the eligibilityDate.
	 *
	 * @return eligibilityDate
	 */
	public Date getEligibilityDate() {
		return this.eligibilityDate;
	}

	/**
	 * Sets the eligibilityDate.
	 *
	 * @param eligibilityDate - eligibilityDate
	 */
	public void setEligibilityDate(final Date eligibilityDate) {
		this.eligibilityDate = eligibilityDate;
	}

	/**
	 * Returns the eligibilityStartDate.
	 *
	 * @return eligibilityStartDate
	 */
	public Date getEligibilityStartDate() {
		return this.eligibilityStartDate;
	}

	/**
	 * Sets the eligibilityStartDate.
	 *
	 * @param eligibilityStartDate - eligibilityStartDate
	 */
	public void setEligibilityStartDate(final Date eligibilityStartDate) {
		this.eligibilityStartDate = eligibilityStartDate;
	}

	/**
	 * Returns the eligibilityEndDate.
	 *
	 * @return eligibilityEndDate
	 */
	public Date getEligibilityEndDate() {
		return this.eligibilityEndDate;
	}

	/**
	 * Sets the eligibilityEndDate.
	 *
	 * @param eligibilityEndDate - eligibilityEndDate
	 */
	public void setEligibilityEndDate(final Date eligibilityEndDate) {
		this.eligibilityEndDate = eligibilityEndDate;
	}

	/**
	 * Returns the release status.
	 *
	 * @return releaseStatus
	 */
	public EarlyReleaseStatusCategory getReleaseStatus() {
		return this.releaseStatus;
	}

	/**
	 * Sets the release status.
	 *
	 * @param releaseStatus - release status
	 */
	public void setReleaseStatus(
			final EarlyReleaseStatusCategory releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	/**
	 * Returns the requestDate.
	 *
	 * @return requestDate
	 */
	public Date getRequestDate() {
		return this.requestDate;
	}

	/**
	 * Sets the requestDate.
	 *
	 * @param requestDate - requestDate
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Returns the requestStartDate.
	 *
	 * @return requestStartDate
	 */
	public Date getRequestStartDate() {
		return this.requestStartDate;
	}

	/**
	 * Sets the requestStartDate.
	 *
	 * @param requestStartDate - requestStartDate
	 */
	public void setRequestStartDate(final Date requestStartDate) {
		this.requestStartDate = requestStartDate;
	}

	/**
	 * Returns the requestEndDate.
	 *
	 * @return requestEndDate
	 */
	public Date getRequestEndDate() {
		return this.requestEndDate;
	}

	/**
	 * Sets the requestEndDate.
	 *
	 * @param requestEndDate - requestEndDate
	 */
	public void setRequestEndDate(final Date requestEndDate) {
		this.requestEndDate = requestEndDate;
	}

	/**
	 * Returns the singleEligibilityDate.
	 *
	 * @return singleEligibilityDate
	 */
	public Boolean getSingleEligibilityDate() {
		return this.singleEligibilityDate;
	}

	/**
	 * Sets the singleEligibilityDate.
	 *
	 * @param singleEligibilityDate - singleEligibilityDate
	 */
	public void setSingleEligibilityDate(final Boolean singleEligibilityDate) {
		this.singleEligibilityDate = singleEligibilityDate;
	}

	/**
	 * Returns the singleRequestDate.
	 *
	 * @return singleRequestDate
	 */
	public Boolean getSingleRequestDate() {
		return this.singleRequestDate;
	}

	/**
	 * Sets the singleRequestDate.
	 *
	 * @param singleRequestDate - singleRequestDate
	 */
	public void setSingleRequestDate(final Boolean singleRequestDate) {
		this.singleRequestDate = singleRequestDate;
	}

	/**
	 * Returns the noResponse.
	 *
	 * @return noResponse
	 */
	public Boolean getNoResponse() {
		return this.noResponse;
	}

	/**
	 * Sets the noResponse.
	 *
	 * @param noResponse - no response
	 */
	public void setNoResponse(final Boolean noResponse) {
		this.noResponse = noResponse;
	}
	
	
}
