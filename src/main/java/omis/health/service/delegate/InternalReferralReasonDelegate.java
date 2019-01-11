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

import omis.health.dao.InternalReferralReasonDao;
import omis.health.domain.InternalReferralReason;
import omis.health.exception.InternalReferralReasonExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for internal referral reason.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 31, 2018)
 * @since OMIS 3.0
 */
public class InternalReferralReasonDelegate {
	private final InstanceFactory<InternalReferralReason>
		internalReferralReasonInstanceFactory;
	private final InternalReferralReasonDao internalReferralReasonDao;
	
	/**
	 * Instantiates a delegate for internal referral reason.
	 * @param internalReferralReasonInstanceFactory
	 * internal referral reason instance factory
	 * @param internalReferralReasonDao internal referral reason dao
	 * 
	 */
	public InternalReferralReasonDelegate(
		final InstanceFactory<InternalReferralReason>
		internalReferralReasonInstanceFactory,
		final InternalReferralReasonDao internalReferralReasonDao) {
		this.internalReferralReasonInstanceFactory
			= internalReferralReasonInstanceFactory;
		this.internalReferralReasonDao = internalReferralReasonDao;
	}

	/**
	 * Creates and persists a internal referral reason.
	 * 
	 * @param provider provider
	 * @param dateRange date range
	 * @param facility facility
	 * @param medicalFacility medical facility
	 * @param title provider title
	 * @param contracted contracted
	 * @throws InternalReferralReasonExistsException if already exists
	 */
	public InternalReferralReason create(
		final String name, final Short sortOrder,
		final Boolean valid) throws InternalReferralReasonExistsException {
		if(this.internalReferralReasonDao.findExisting(name)!=null) {
			throw new InternalReferralReasonExistsException("Internal"
					+ "referral reason already exists"); 
		}
		InternalReferralReason reason
		= this.internalReferralReasonInstanceFactory.createInstance();
		reason.setValid(valid);
		reason.setSortOrder(sortOrder);
		reason.setName(name);

		return this.internalReferralReasonDao.makePersistent(reason);
	}
}