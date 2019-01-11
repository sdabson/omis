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
package omis.probationparole.service.delegate;

import java.util.Date;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.probationparole.dao.ProbationParoleDocumentAssociationDao;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.domain.ProbationParoleDocumentCategory;

/**
 * Probation Parole Document Association Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Probation and Parole Document already exists with given Docket "
			+ "and Document.";
	
	private final ProbationParoleDocumentAssociationDao
			probationParoleDocumentAssociationDao;
	
	private final InstanceFactory<ProbationParoleDocumentAssociation> 
		probationParoleDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ProbationParoleDocumentAssociationDelegate.
	 * 
	 * @param probationParoleDocumentAssociationDao - Probation Parole Document
	 * Association DAO
	 * @param probationParoleDocumentAssociationInstanceFactory - Probation
	 * Parole Document Association Instance Factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public ProbationParoleDocumentAssociationDelegate(
			final ProbationParoleDocumentAssociationDao
				probationParoleDocumentAssociationDao,
			final InstanceFactory<ProbationParoleDocumentAssociation> 
				probationParoleDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.probationParoleDocumentAssociationDao =
				probationParoleDocumentAssociationDao;
		this.probationParoleDocumentAssociationInstanceFactory =
				probationParoleDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Probation Parole Document Association with the
	 * given properties.
	 * 
	 * @param document - Document
	 * @param offender - Offender
	 * @param date - Date
	 * @param category - Probation Parole Document Category
	 * @return Newly created Probation Parole Document Association
	 * @throws DuplicateEntityFoundException - When a Probation Parole
	 * Document Association already exists with the given Document and Docket.
	 */
	public ProbationParoleDocumentAssociation create(final Document document,
			final Offender offender, final Date date,
			final ProbationParoleDocumentCategory category)
					throws DuplicateEntityFoundException {
		if (this.probationParoleDocumentAssociationDao.find(document)
				!= null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ProbationParoleDocumentAssociation probationParoleDocumentAssociation = 
				this.probationParoleDocumentAssociationInstanceFactory
				.createInstance();
		
		probationParoleDocumentAssociation.setCategory(category);
		probationParoleDocumentAssociation.setDate(date);
		probationParoleDocumentAssociation.setDocument(document);
		probationParoleDocumentAssociation.setOffender(offender);
		probationParoleDocumentAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		probationParoleDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.probationParoleDocumentAssociationDao
				.makePersistent(probationParoleDocumentAssociation);
	}
	
	/**
	 * Updates the specified Probation Parole Document Association with the
	 * given properties.
	 * 
	 * @param probationParoleDocumentAssociation - Probation Parole Document
	 * Association to update
	 * @param document - Document
	 * @param offender - Offender
	 * @param date - Date
	 * @param category - Probation Parole Document Category
	 * @return Updated Probation Parole Document Association
	 * @throws DuplicateEntityFoundException - When a Probation Parole
	 * Document Association already exists with the given Document and Docket.
	 */
	public ProbationParoleDocumentAssociation update(
			final ProbationParoleDocumentAssociation
				probationParoleDocumentAssociation,
				final Document document, final Offender offender, 
				final Date date,
				final ProbationParoleDocumentCategory category)
			throws DuplicateEntityFoundException {
		if (this.probationParoleDocumentAssociationDao.findExcluding(document, 
				probationParoleDocumentAssociation) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		probationParoleDocumentAssociation.setCategory(category);
		probationParoleDocumentAssociation.setDate(date);
		probationParoleDocumentAssociation.setDocument(document);
		probationParoleDocumentAssociation.setOffender(offender);
		probationParoleDocumentAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.probationParoleDocumentAssociationDao
				.makePersistent(probationParoleDocumentAssociation);
	}
	
	/**
	 * Removes the specified Probation Parole Document Association.
	 * 
	 * @param probationParoleDocumentAssociation - Probation Parole Document
	 * Association to remove.
	 */
	public void remove(final ProbationParoleDocumentAssociation
			probationParoleDocumentAssociation) {
		this.probationParoleDocumentAssociationDao.makeTransient(
				probationParoleDocumentAssociation);
	}
	
	
	
}
