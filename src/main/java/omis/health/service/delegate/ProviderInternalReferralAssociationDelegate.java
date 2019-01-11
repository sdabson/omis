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

import omis.health.dao.ProviderInternalReferralAssociationDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;
import omis.health.exception.ProviderInternalReferralAssociationExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for provider internal referral association.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 31, 2018)
 * @since OMIS 3.0
 */
public class ProviderInternalReferralAssociationDelegate {
	
	private final InstanceFactory<ProviderInternalReferralAssociation>
	providerInternalReferralAssociationInstanceFactory;
	
	private final ProviderInternalReferralAssociationDao
	providerInternalReferralAssociationDao;
	
	/**
	 * Instantiates a delegate for provider internal referral association.
	 * 
	 * @param providerInternalReferralAssociationInstanceFactory
	 * instance factory for provider internal referral association
	 * @param providerInternalReferralAssociationDao data access object
	 * for provider internal referral association
	 */
	public ProviderInternalReferralAssociationDelegate(
			final InstanceFactory<ProviderInternalReferralAssociation>
			providerInternalReferralAssociationInstanceFactory,
			final ProviderInternalReferralAssociationDao
			providerInternalReferralAssociationDao) {
		this.providerInternalReferralAssociationInstanceFactory
		= providerInternalReferralAssociationInstanceFactory;
		this.providerInternalReferralAssociationDao
		= providerInternalReferralAssociationDao;
	}

	/**
	 * Creates and persists a provider internal referral association.
	 * 
	 * @param providerAssignment provider assignment
	 * @param internalReferral internal referral
	 * @param primary primary
	 * @param cancelled cancelled
	 * @return ProviderInternalReferralAssociation provider internal
	 * referral association
	 * @throws ProviderInternalReferralAssociationExistsException if
	 * already exists
	 */
	public ProviderInternalReferralAssociation create(
		final ProviderAssignment providerAssignment,
		final InternalReferral internalReferral,
		final Boolean primary, final boolean cancelled)
			throws ProviderInternalReferralAssociationExistsException {
		if(this.providerInternalReferralAssociationDao.find(
			providerAssignment, internalReferral)!=null) {
			throw new ProviderInternalReferralAssociationExistsException(
				"Provider internal referral association already exists");
		}
		
		ProviderInternalReferralAssociation pira
		= this.providerInternalReferralAssociationInstanceFactory
			.createInstance();
		pira.setProviderAssignment(providerAssignment);
		pira.setPrimary(true);
		pira.setInternalReferral(internalReferral);
		pira.setCancelled(true);
		return this.providerInternalReferralAssociationDao
			.makePersistent(pira);
		
	}
}