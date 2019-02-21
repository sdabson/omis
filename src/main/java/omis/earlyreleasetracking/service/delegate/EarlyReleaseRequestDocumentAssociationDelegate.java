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

import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestDocumentAssociationDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestDocumentAssociationExists;
import omis.instance.factory.InstanceFactory;

/**
 * Early Release Request Document Association Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Request Document Association already exists with "
			+ "the given Document and Early Release Request";
	
	private final EarlyReleaseRequestDocumentAssociationDao
			earlyReleaseRequestDocumentAssociationDao;
	
	private final InstanceFactory<EarlyReleaseRequestDocumentAssociation>
			earlyReleaseRequestDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseRequestDocumentAssociationDelegate.
	 * 
	 * @param earlyReleaseRequestDocumentAssociationDao Early Release Request
	 * Document Assocation DAO
	 * @param earlyReleaseRequestDocumentAssociationInstanceFactory Early
	 * Release Request Document Association Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseRequestDocumentAssociationDelegate(
			final EarlyReleaseRequestDocumentAssociationDao
				earlyReleaseRequestDocumentAssociationDao,
			final InstanceFactory<EarlyReleaseRequestDocumentAssociation> 
				earlyReleaseRequestDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseRequestDocumentAssociationDao =
				earlyReleaseRequestDocumentAssociationDao;
		this.earlyReleaseRequestDocumentAssociationInstanceFactory =
				earlyReleaseRequestDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates a Early Release Request Document Association with
	 * the given properties.
	 * 
	 * @param document Document
	 * @param earlyReleaseRequest Early Release Request
	 * @return Newly created Early Release Request Document Association
	 * @throws EarlyReleaseRequestDocumentAssociationExists When a Early
	 * Release Request Document Association already exists with the given
	 * Document and Early Release Request.
	 */
	public EarlyReleaseRequestDocumentAssociation create(
			final Document document,
			final EarlyReleaseRequest earlyReleaseRequest)
					throws EarlyReleaseRequestDocumentAssociationExists {
		if (this.earlyReleaseRequestDocumentAssociationDao.find(
				earlyReleaseRequest, document) != null) {
			throw new EarlyReleaseRequestDocumentAssociationExists(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociation = 
					this.earlyReleaseRequestDocumentAssociationInstanceFactory
					.createInstance();
		
		earlyReleaseRequestDocumentAssociation.setDocument(document);
		earlyReleaseRequestDocumentAssociation.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestDocumentAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseRequestDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestDocumentAssociationDao.makePersistent(
				earlyReleaseRequestDocumentAssociation);
	}
	
	/**
	 * Updates the specified Early Release Request Document Association with
	 * the given properties.
	 * 
	 * @param earlyReleaseRequestDocumentAssociation Early Release Request
	 * Document Association to update
	 * @param document Document
	 * @param earlyReleaseRequest Early Release Request
	 * @return Updated Early Release Request Document Association
	 * @throws EarlyReleaseRequestDocumentAssociationExists When a Early Release
	 * Request Document Association already exists with the given Document and
	 * Early Release Request.
	 */
	public EarlyReleaseRequestDocumentAssociation update(
			final EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociation, final Document document,
			final EarlyReleaseRequest earlyReleaseRequest)
						throws EarlyReleaseRequestDocumentAssociationExists {
		if (this.earlyReleaseRequestDocumentAssociationDao.findExcluding(
				earlyReleaseRequest, document,
				earlyReleaseRequestDocumentAssociation) != null) {
			throw new EarlyReleaseRequestDocumentAssociationExists(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		earlyReleaseRequestDocumentAssociation.setDocument(document);
		earlyReleaseRequestDocumentAssociation.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestDocumentAssociationDao.makePersistent(
				earlyReleaseRequestDocumentAssociation);
	}
	
	/**
	 * Removes the given Early Release Request Document Association.
	 * 
	 * @param earlyReleaseRequestDocumentAssociation Early Release Request
	 * Document Association to remove
	 */
	public void remove(final EarlyReleaseRequestDocumentAssociation
			earlyReleaseRequestDocumentAssociation) {
		this.earlyReleaseRequestDocumentAssociationDao.makeTransient(
				earlyReleaseRequestDocumentAssociation);
	}
	
	/**
	 * Returns a list of Early Release Request Document Associations for the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Document Associations for the
	 * specified Early Release Request.
	 */
	public List<EarlyReleaseRequestDocumentAssociation>
				findByEarlyReleaseRequest(
						final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestDocumentAssociationDao
			.findByEarlyReleaseRequest(earlyReleaseRequest);
	}
	
}
