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
package omis.prisonterm.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.prisonterm.dao.PrisonTermDocumentAssociationDao;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermDocumentAssociation;

/**
 * Prison Term Document Association Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2018)
 *@since OMIS 3.0
 *
 */
public class PrisonTermDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Prison Term Document Association already exists with given"
			+ "Document and Prison Term.";
	
	private final PrisonTermDocumentAssociationDao
			prisonTermDocumentAssociationDao;
	
	private final InstanceFactory<PrisonTermDocumentAssociation> 
		prisonTermDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PrisonTermDocumentAssociationDelegate.
	 * 
	 * @param prisonTermDocumentAssociationDao Prison Term Document Association
	 * Data Access Object
	 * @param prisonTermDocumentAssociationInstanceFactory Prison Term Document
	 * Association Instance Factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public PrisonTermDocumentAssociationDelegate(
			final PrisonTermDocumentAssociationDao
				prisonTermDocumentAssociationDao,
			final InstanceFactory<PrisonTermDocumentAssociation> 
				prisonTermDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.prisonTermDocumentAssociationDao =
				prisonTermDocumentAssociationDao;
		this.prisonTermDocumentAssociationInstanceFactory =
				prisonTermDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Prison Term Document Association with the specified Document
	 * and Prison Term.
	 * 
	 * @param document Document
	 * @param prisonTerm Prison Term
	 * @return Created Prison Term Document Association
	 * @throws DuplicateEntityFoundException - When a Prison Term Document
	 * Association already exists with the given Document and Prison Term.
	 */
	public PrisonTermDocumentAssociation create(final Document document,
			final PrisonTerm prisonTerm)
					throws DuplicateEntityFoundException {
		if (this.prisonTermDocumentAssociationDao.find(
				document, prisonTerm) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PrisonTermDocumentAssociation prisonTermDocumentAssociation = 
				this.prisonTermDocumentAssociationInstanceFactory
				.createInstance();

		prisonTermDocumentAssociation.setDocument(document);
		prisonTermDocumentAssociation.setPrisonTerm(prisonTerm);
		prisonTermDocumentAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		prisonTermDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.prisonTermDocumentAssociationDao.makePersistent(
				prisonTermDocumentAssociation);
	}
	
	/**
	 * Updates the given Prison Term Document Association with the specified
	 * Document and Prison Term.
	 * 
	 * @param prisonTermDocumentAssociation Prison Term Document Association to
	 * update
	 * @param document Document
	 * @param prisonTerm Prison Term
	 * @return Updated Prison Term Document Association
	 * @throws DuplicateEntityFoundException - When a Prison Term Document
	 * Association already exists with the given Document and Prison Term.
	 */
	public PrisonTermDocumentAssociation update(
			final PrisonTermDocumentAssociation prisonTermDocumentAssociation,
			final Document document, final PrisonTerm prisonTerm)
					throws DuplicateEntityFoundException {
		if (this.prisonTermDocumentAssociationDao.findExcluding(document,
				prisonTerm, prisonTermDocumentAssociation) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		prisonTermDocumentAssociation.setDocument(document);
		prisonTermDocumentAssociation.setPrisonTerm(prisonTerm);
		prisonTermDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.prisonTermDocumentAssociationDao.makePersistent(
				prisonTermDocumentAssociation);
	}
	
	/**
	 * Removes the specified Prison Term Document Association.
	 * 
	 * @param prisonTermDocumentAssociation Prison Term Document Association
	 */
	public void remove(final PrisonTermDocumentAssociation
			prisonTermDocumentAssociation) {
		this.prisonTermDocumentAssociationDao.makeTransient(
				prisonTermDocumentAssociation);
	}
	
}
