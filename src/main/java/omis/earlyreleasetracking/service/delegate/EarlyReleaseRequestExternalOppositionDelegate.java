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
package omis.earlyreleasetracking.service.delegate;

import java.util.Date;
import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestExternalOppositionDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestExternalOpposition;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Early Release Request External Opposition Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestExternalOppositionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Request External Opposition already exists with the "
			+ "given Party for the specified Early Release Request.";
	
	private final EarlyReleaseRequestExternalOppositionDao
		earlyReleaseRequestExternalOppositionDao;
	
	private final InstanceFactory<EarlyReleaseRequestExternalOpposition> 
		earlyReleaseRequestExternalOppositionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseRequestExternalOppositionDelegate.
	 * 
	 * @param earlyReleaseRequestExternalOppositionDao Early Release Request
	 * External Opposition DAO
	 * @param earlyReleaseRequestExternalOppositionInstanceFactory Early Release
	 * Request External Opposition Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseRequestExternalOppositionDelegate(
			final EarlyReleaseRequestExternalOppositionDao
				earlyReleaseRequestExternalOppositionDao,
			final InstanceFactory<EarlyReleaseRequestExternalOpposition> 
				earlyReleaseRequestExternalOppositionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseRequestExternalOppositionDao =
				earlyReleaseRequestExternalOppositionDao;
		this.earlyReleaseRequestExternalOppositionInstanceFactory =
				earlyReleaseRequestExternalOppositionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates a new Early Release Request External Opposition with
	 * the given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param party Party
	 * @param date Date
	 * @param narrative Narrative
	 * @return Newly created Early Release Request External Opposition
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * External Opposition already exists with the given party for the specified
	 * Early Release Request
	 */
	public EarlyReleaseRequestExternalOpposition create(
			final EarlyReleaseRequest earlyReleaseRequest,
			final ExternalOppositionPartyCategory party, final Date date,
			final String narrative)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseRequestExternalOppositionDao.find(
				earlyReleaseRequest, party) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseRequestExternalOpposition
			earlyReleaseRequestExternalOpposition =
				this.earlyReleaseRequestExternalOppositionInstanceFactory
				.createInstance();

		earlyReleaseRequestExternalOpposition.setDate(date);
		earlyReleaseRequestExternalOpposition.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestExternalOpposition.setNarrative(narrative);
		earlyReleaseRequestExternalOpposition.setParty(party);
		earlyReleaseRequestExternalOpposition.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseRequestExternalOpposition.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestExternalOppositionDao.makePersistent(
				earlyReleaseRequestExternalOpposition);
	}
	
	/**
	 * Updates the specified Early Release Request External Opposition with
	 * the given properties.
	 * 
	 * @param earlyReleaseRequestExternalOpposition Early Release Request
	 * External Opposition to update
	 * @param earlyReleaseRequest Early Release Request
	 * @param party Party
	 * @param date Date
	 * @param narrative Narrative
	 * @return Updated Early Release Request External Opposition
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * External Opposition already exists with the given party for the specified
	 * Early Release Request
	 */
	public EarlyReleaseRequestExternalOpposition update(
			final EarlyReleaseRequestExternalOpposition
				earlyReleaseRequestExternalOpposition,
			final EarlyReleaseRequest earlyReleaseRequest,
			final ExternalOppositionPartyCategory party, final Date date,
			final String narrative)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseRequestExternalOppositionDao.findExcluding(
				earlyReleaseRequest, party,
				earlyReleaseRequestExternalOpposition) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		earlyReleaseRequestExternalOpposition.setDate(date);
		earlyReleaseRequestExternalOpposition.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestExternalOpposition.setNarrative(narrative);
		earlyReleaseRequestExternalOpposition.setParty(party);
		earlyReleaseRequestExternalOpposition.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestExternalOppositionDao.makePersistent(
				earlyReleaseRequestExternalOpposition);
	}
	
	/**
	 * Removes the specified Early Release Request External Opposition.
	 * 
	 * @param earlyReleaseRequestExternalOpposition Early Release Request
	 * External Opposition to remove
	 */
	public void remove(final EarlyReleaseRequestExternalOpposition
			earlyReleaseRequestExternalOpposition) {
		this.earlyReleaseRequestExternalOppositionDao.makeTransient(
				earlyReleaseRequestExternalOpposition);
	}
	
	/**
	 * Returns a list of Early Release Request External Oppositions for the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request External Oppositions for the
	 * specified Early Release Request.
	 */
	public List<EarlyReleaseRequestExternalOpposition>
				findByEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestExternalOppositionDao
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}
	
}
