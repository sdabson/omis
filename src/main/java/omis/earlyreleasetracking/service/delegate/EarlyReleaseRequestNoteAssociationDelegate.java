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
import omis.earlyreleasetracking.dao.EarlyReleaseRequestNoteAssociationDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestNoteAssociation;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestNoteAssociationExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Early Release Request Note Association Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestNoteAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Request Note Association already exists with the "
			+ "given date and description for the specified Early Release "
			+ "Request.";
	
	private final EarlyReleaseRequestNoteAssociationDao
		earlyReleaseRequestNoteAssociationDao;
	
	private final InstanceFactory<EarlyReleaseRequestNoteAssociation> 
		earlyReleaseRequestNoteAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseRequestNoteAssociationDelegate.
	 * 
	 * @param earlyReleaseRequestNoteAssociationDao Early Release Request
	 * Note Association DAO
	 * @param earlyReleaseRequestNoteAssociationInstanceFactory Early Release
	 * Request Note Association Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseRequestNoteAssociationDelegate(
			final EarlyReleaseRequestNoteAssociationDao
				earlyReleaseRequestNoteAssociationDao,
			final InstanceFactory<EarlyReleaseRequestNoteAssociation> 
				earlyReleaseRequestNoteAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseRequestNoteAssociationDao =
				earlyReleaseRequestNoteAssociationDao;
		this.earlyReleaseRequestNoteAssociationInstanceFactory =
				earlyReleaseRequestNoteAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates an Early Release Request Note Association with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param description Description
	 * @param date Date
	 * @return Newly created Early Release Request Note Association
	 * @throws EarlyReleaseRequestNoteAssociationExistsException When a Early
	 * Release Request Note Association already exists with the given Date,
	 * description, and Early Release Request
	 */
	public EarlyReleaseRequestNoteAssociation create(
			final EarlyReleaseRequest earlyReleaseRequest,
			final String description, final Date date)
					throws EarlyReleaseRequestNoteAssociationExistsException {
		if (this.earlyReleaseRequestNoteAssociationDao.find(
				earlyReleaseRequest, description, date) != null) {
			throw new EarlyReleaseRequestNoteAssociationExistsException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseRequestNoteAssociation earlyReleaseRequestNoteAssociation =
				this.earlyReleaseRequestNoteAssociationInstanceFactory
				.createInstance();
		
		earlyReleaseRequestNoteAssociation.setDate(date);
		earlyReleaseRequestNoteAssociation.setDescription(description);
		earlyReleaseRequestNoteAssociation.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestNoteAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseRequestNoteAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestNoteAssociationDao.makePersistent(
				earlyReleaseRequestNoteAssociation);
	}
	
	/**
	 * Updates the specified Early Release Request Note Association with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequestNoteAssociation Early Release Request Note
	 * Association to update
	 * @param earlyReleaseRequest Early Release Request
	 * @param description Description
	 * @param date Date
	 * @return Updated Early Release Request Note Association
	 * @throws EarlyReleaseRequestNoteAssociationExistsException When a Early
	 * Release Request Note Association already exists with the given Date,
	 * description, and Early Release Request
	 */
	public EarlyReleaseRequestNoteAssociation update(
			final EarlyReleaseRequestNoteAssociation
				earlyReleaseRequestNoteAssociation,
			final EarlyReleaseRequest earlyReleaseRequest,
			final String description, final Date date)
					throws EarlyReleaseRequestNoteAssociationExistsException {
		if (this.earlyReleaseRequestNoteAssociationDao.findExcluding(
				earlyReleaseRequest, description, date,
				earlyReleaseRequestNoteAssociation) != null) {
			throw new EarlyReleaseRequestNoteAssociationExistsException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}

		earlyReleaseRequestNoteAssociation.setDate(date);
		earlyReleaseRequestNoteAssociation.setDescription(description);
		earlyReleaseRequestNoteAssociation.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestNoteAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestNoteAssociationDao.makePersistent(
				earlyReleaseRequestNoteAssociation);
	}
	
	/**
	 * Removes the specified Early Release Request Note Association.
	 * 
	 * @param earlyReleaseRequestNoteAssociation Early Release Request Note
	 * Association to remove
	 */
	public void remove(final EarlyReleaseRequestNoteAssociation
			earlyReleaseRequestNoteAssociation) {
		this.earlyReleaseRequestNoteAssociationDao.makeTransient(
				earlyReleaseRequestNoteAssociation);
	}
	
	/**
	 * Returns a list of Early Release Request Note Associations by the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Note Associations by the
	 * specified Early Release Request.
	 */
	public List<EarlyReleaseRequestNoteAssociation> findByEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestNoteAssociationDao
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}
}
