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
package omis.earlyreleasetracking.domain;

import java.util.Date;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.docket.domain.Docket;
import omis.user.domain.UserAccount;

/**
 * Early Release Request.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 7, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequest extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Early Release Request.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the Early Release Request.
	 * @param id - ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the Docket for the Early Release Request.
	 * @return docket - Docket
	 */
	Docket getDocket();
	
	/**
	 * Sets the Docket for the Early Release Request.
	 * @param docket - Docket
	 */
	void setDocket(Docket docket);
	
	/**
	 * Returns the Request Date for the Early Release Request.
	 * @return requestDate - Request Date
	 */
	Date getRequestDate();
	
	/**
	 * Sets the Request Date for the Early Release Request.
	 * @param requestDate - Request Date
	 */
	void setRequestDate(Date requestDate);
	
	/**
	 * Returns the Request Status for the Early Release Request.
	 * @return requestStatus - Request Status
	 */
	EarlyReleaseStatusCategory getRequestStatus();
	
	/**
	 * Sets the Request Status for the Early Release Request.
	 * @param requestStatus - Request Status
	 */
	void setRequestStatus(EarlyReleaseStatusCategory requestStatus);
	
	/**
	 * Returns the Judge Response for the Early Release Request.
	 * @return judgeResponse - Judge Response
	 */
	EarlyReleaseJudgeResponseCategory getJudgeResponse();
	
	/**
	 * Sets the Judge Response for the Early Release Request.
	 * @param judgeResponse - Judge Response
	 */
	void setJudgeResponse(EarlyReleaseJudgeResponseCategory judgeResponse);
	
	/**
	 * Returns the Category for the Early Release Request.
	 * @return category - Category
	 */
	EarlyReleaseRequestCategory getCategory();
	
	/**
	 * Sets the Category for the Early Release Request.
	 * @param category - Category
	 */
	void setCategory(EarlyReleaseRequestCategory category);
	
	/**
	 * Returns the Request By for the Early Release Request.
	 * @return requestBy - Request By
	 */
	UserAccount getRequestBy();
	
	/**
	 * Sets the Request By for the Early Release Request.
	 * @param requestBy - Request By
	 */
	void setRequestBy(UserAccount requestBy);
	
	/**
	 * Returns the Approval Date for the Early Release Request.
	 * @return approvalDate - Approval Date
	 */
	Date getApprovalDate();
	
	/**
	 * Sets the Approval Date for the Early Release Request.
	 * @param approvalDate - Approval Date
	 */
	void setApprovalDate(Date approvalDate);
	
	/**
	 * Returns the Comments for the Early Release Request.
	 * @return comments - Comments
	 */
	String getComments();
	
	/**
	 * Sets the Comments for the Early Release Request.
	 * @param comments - Comments
	 */
	void setComments(String comments);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
