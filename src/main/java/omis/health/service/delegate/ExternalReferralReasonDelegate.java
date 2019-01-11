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
package omis.health.service.delegate;

import omis.exception.DuplicateEntityFoundException;
import omis.health.dao.ExternalReferralReasonDao;
import omis.health.domain.ExternalReferralReason;
import omis.health.exception.ExternalReferralReasonExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for external referral reason.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public class ExternalReferralReasonDelegate {
	private final InstanceFactory<ExternalReferralReason>
		externalReferralReasonInstanceFactory;
	private final ExternalReferralReasonDao
		externalReferralReasonDao;
	
	/**
	 * Instantiates a delegate for external referral reason
	 * 
	 * @param externalReferralReasonInstanceFactory
	 * externalReferralReasonInstanceFactory
	 * @param externalReferralReasonDao
	 * externalReferralReasonDao
	 * 
	 */
	public ExternalReferralReasonDelegate(
		final InstanceFactory<ExternalReferralReason>
		externalReferralReasonInstanceFactory,
		final ExternalReferralReasonDao
		externalReferralReasonDao) {
		this.externalReferralReasonInstanceFactory
			= externalReferralReasonInstanceFactory;
		this.externalReferralReasonDao = externalReferralReasonDao;
	}

	/**
	 * Creates and persists a external referral reason.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @throws DuplicateEntityFoundException if reason exists
	 */
	public ExternalReferralReason create(
		final String name, final Short sortOrder,
		final Boolean valid)
			throws ExternalReferralReasonExistsException {
		if(this.externalReferralReasonDao.findExisting(name)!=null) {
			throw new ExternalReferralReasonExistsException(""
			+ "External referral reason already exists");
		}
		ExternalReferralReason reason
		= this.externalReferralReasonInstanceFactory
			.createInstance();
		reason.setValid(valid);
		reason.setSortOrder(sortOrder);
		reason.setName(name);
		return this.externalReferralReasonDao.makePersistent(reason);
	}
}