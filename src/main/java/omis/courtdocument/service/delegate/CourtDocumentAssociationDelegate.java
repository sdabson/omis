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
package omis.courtdocument.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtdocument.dao.CourtDocumentAssociationDao;
import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/** 
 * Delegate for court document service related operations.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.1 (Aug 6, 2018)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationDelegate {
	
	private final CourtDocumentAssociationDao 
		courtDocumentAssociationDao;
	
	private final InstanceFactory<CourtDocumentAssociation> 
		courtDocumentAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Exception messages. */
	
	private static final String COURT_DOCUMENT_ASSOCIATION_EXISTS_MSG  = 
			"Court document exists.";
	
	/** 
	 * Constructor.
	 * 
	 * @param courtDocumentAssociationInstanceFactory court document association 
	 * instance factory
	 * @param courtDocumentAssociationDao court document association data access 
	 * object
	 * @param auditComponentRetriever audit component retriever
	 */
	public CourtDocumentAssociationDelegate(
			final InstanceFactory<CourtDocumentAssociation> 
				courtDocumentAssociationInstanceFactory, 
			final CourtDocumentAssociationDao 
				courtDocumentAssociationDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.courtDocumentAssociationInstanceFactory = 
				courtDocumentAssociationInstanceFactory;
		this.courtDocumentAssociationDao = courtDocumentAssociationDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** 
	 * Creates a court document association.
	 * 
	 * @param docket docket
	 * @param offender offender
	 * @param document document
	 * @param date date
	 * @param category court document category
	 * @return court document association
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public CourtDocumentAssociation create(final Docket docket,
			final Offender offender, final Document document, final Date date, 
			final CourtDocumentCategory category) 
					throws DuplicateEntityFoundException {
		if (this.courtDocumentAssociationDao.findByDocument(document).size()
				> 0) {
			throw new DuplicateEntityFoundException(
					COURT_DOCUMENT_ASSOCIATION_EXISTS_MSG);
		}
		
		final CourtDocumentAssociation courtDocumentAssociation = 
				this.courtDocumentAssociationInstanceFactory
				.createInstance();
		this.populate(courtDocumentAssociation, docket, offender, document, 
				date, category);
		courtDocumentAssociation.setCreationSignature(
				this.retrieveCreationSignature());
		courtDocumentAssociation.setUpdateSignature(
				this.retrieveUpdateSignature());
		return this.courtDocumentAssociationDao.makePersistent(
				courtDocumentAssociation);
	}
	
	/** 
	 * Updates a court document association.
	 * 
	 * @param courtDocumentAssociation court case document association
	 * @param docket docket
	 * @param offender offender
	 * @param document document
	 * @param date date
	 * @param courtDocumentCategory court document category 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public CourtDocumentAssociation update(
			final CourtDocumentAssociation courtDocumentAssociation, 
			final Docket docket, final Offender offender, 
			final Document document, final Date date, 
			final CourtDocumentCategory courtDocumentCategory) 
					throws DuplicateEntityFoundException {
		if (this.courtDocumentAssociationDao.findByDocumentExcluding(
				document, courtDocumentAssociation).size() > 0) {
			throw new DuplicateEntityFoundException(
					COURT_DOCUMENT_ASSOCIATION_EXISTS_MSG);
			
		}
		populate(courtDocumentAssociation, docket, offender, document, date,
				courtDocumentCategory);
		courtDocumentAssociation.setUpdateSignature(this
				.retrieveUpdateSignature());
		return this.courtDocumentAssociationDao.makePersistent(
				courtDocumentAssociation);
	}
	
	/** 
	 * Removes court document association.
	 * 
	 * @param courtDocumentAssociation court document association
	 */
	public void remove(
			final CourtDocumentAssociation courtDocumentAssociation) {
		this.courtDocumentAssociationDao.makeTransient(
				courtDocumentAssociation);
	}
	
	/** 
	 * Finds court document association count by offender.
	 * 
	 * @param offender offender
	 * @return count
	 */
	public Integer findCourtDocumentAssociationCountByOffender(
			final Offender offender) {
		return this.courtDocumentAssociationDao.findCountByOffender(
				offender);
	}
		
	/* populate court document association. */
	private void populate(
			final CourtDocumentAssociation courtDocumentAssociation,
			final Docket docket, final Offender offender, 
			final Document document, final Date date,
			final CourtDocumentCategory category) {
		courtDocumentAssociation.setDocket(docket);
		courtDocumentAssociation.setOffender(offender);
		courtDocumentAssociation.setDate(date);
		courtDocumentAssociation.setDocument(document);
		courtDocumentAssociation.setCategory(category);
	}
	
	/* Retrieve creation signature. */
	private CreationSignature retrieveCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate());
	}
	
	/** Retrieve update signature. */
	private UpdateSignature retrieveUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}