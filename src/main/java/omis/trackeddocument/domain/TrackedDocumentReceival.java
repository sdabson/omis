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
package omis.trackeddocument.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.docket.domain.Docket;
import omis.user.domain.UserAccount;

/**
 * Tracked document receival.
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Dec 11, 2017)
 * @since: OMIS 3.0
 */
public interface TrackedDocumentReceival extends Updatable, Creatable {
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	Docket getDocket();
	
	/**
	 * Sets the docket.
	 * 
	 * @param docket docket
	 */
	void setDocket(Docket docket);

	/**
	 * Sets the received date.
	 * 
	 * @param receivedDate received date
	 */
	void setReceivedDate(Date receivedDate);

	/**
	 * Gets the received date.
	 * 
	 * @return receivedDate received date
	 */
	Date getReceivedDate();

	/**
	 * Sets the received user account.
	 * 
	 * @param receivedByUserAccount received by user account
	 */
	void setReceivedByUserAccount(UserAccount receivedByUserAccount);

	/**
	 * Gets the received user account.
	 * 
	 * @return receivedByUserAccount received by user account
	 */
	UserAccount getReceivedByUserAccount();
	
	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	void setCategory(TrackedDocumentCategory category);

	/**
	 * Gets the category.
	 * 
	 * @return category category
	 */
	TrackedDocumentCategory getCategory();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}