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
package omis.unitmanagerreview.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.unitmanagerreview.dao.UnitManagerReviewDocumentAssociationDao;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;

/**
 * Unit manager review document association delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewDocumentAssociationDelegate {

	/* Data access objects. */
	
	private final UnitManagerReviewDocumentAssociationDao 
			unitManagerReviewDocumentAssociationDao;

	/* Instance factories. */
	
	private final InstanceFactory<UnitManagerReviewDocumentAssociation> 
			unitManagerReviewDocumentAssociationInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;

	/** Instantiates an implementation of unit manager review document 
	 * association delegate with the specified date access object and instance 
	 * factory.
	 * 
	 * @param unitManagerReviewDocumentAssociationDao unit manager review 
	 * document association data access object
	 * @param unitManagerReviewDocumentAssociationInstanceFactory unit manager 
	 * review document association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public UnitManagerReviewDocumentAssociationDelegate(
			final UnitManagerReviewDocumentAssociationDao 
					unitManagerReviewDocumentAssociationDao,
			final InstanceFactory<UnitManagerReviewDocumentAssociation> 
					unitManagerReviewDocumentAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.unitManagerReviewDocumentAssociationDao = 
				unitManagerReviewDocumentAssociationDao;
		this.unitManagerReviewDocumentAssociationInstanceFactory = 
				unitManagerReviewDocumentAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Create a new unit manager review document association.
	 * 
	 * @param document document
	 * @param unitManagerReview unit manager review
	 * @return unit manager review document association
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public UnitManagerReviewDocumentAssociation create(final Document document, 
			final UnitManagerReview unitManagerReview) 
					throws DuplicateEntityFoundException {
		if (this.unitManagerReviewDocumentAssociationDao.find(document, 
				unitManagerReview) != null) {
			throw new DuplicateEntityFoundException(
					"Unit manager review document association already exists.");
		}
		UnitManagerReviewDocumentAssociation documentAssociation = this
				.unitManagerReviewDocumentAssociationInstanceFactory
				.createInstance();
		documentAssociation.setDocument(document);
		documentAssociation.setUnitManagerReview(unitManagerReview);
		documentAssociation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		documentAssociation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.unitManagerReviewDocumentAssociationDao.makePersistent(
				documentAssociation);
	}
	
	/**
	 * Removes the specified unit manager review document association.
	 * 
	 * @param unitManagerReviewDocumentAssociation unit manager review document 
	 * association
	 */
	public void remove(
			final UnitManagerReviewDocumentAssociation 
					unitManagerReviewDocumentAssociation) {
		this.unitManagerReviewDocumentAssociationDao.makeTransient(
				unitManagerReviewDocumentAssociation);
	}

	/**
	 * Returns a list of unit manager review document associations for the 
	 * specified unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 * @return list of unit manager review document associations
	 */
	public List<UnitManagerReviewDocumentAssociation> findByUnitManagerReview(
			final UnitManagerReview unitManagerReview) {
		return this.unitManagerReviewDocumentAssociationDao
				.findByUnitManagerReview(unitManagerReview);
	}
}
