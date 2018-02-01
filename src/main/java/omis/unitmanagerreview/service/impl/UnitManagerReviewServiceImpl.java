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
package omis.unitmanagerreview.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;
import omis.unitmanagerreview.service.UnitManagerReviewService;
import omis.unitmanagerreview.service.delegate.UnitManagerReviewDelegate;
import omis.unitmanagerreview.service.delegate.UnitManagerReviewDocumentAssociationDelegate;

/**
 * Implementation for unit manager review service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewServiceImpl implements UnitManagerReviewService {

	/* Delegates. */
	
	private final UnitManagerReviewDelegate unitManagerReviewDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	private final UnitManagerReviewDocumentAssociationDelegate 
			unitManagerReviewDocumentAssociationDelegate;
	
	public UnitManagerReviewServiceImpl(
			final UnitManagerReviewDelegate unitManagerReviewDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final UnitManagerReviewDocumentAssociationDelegate
					unitManagerReviewDocumentAssociationDelegate) {
		this.unitManagerReviewDelegate = unitManagerReviewDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.unitManagerReviewDocumentAssociationDelegate = 
				unitManagerReviewDocumentAssociationDelegate;
	}
	
	/**{@inheritDoc} */
	@Override
	public UnitManagerReview createUnitManagerReview(
			final StaffAssignment staffAssignment, final Date date, 
			final String text, final Offender offender) 
					throws DuplicateEntityFoundException {
		return this.unitManagerReviewDelegate.create(staffAssignment, date, 
				text, offender);
	}

	/**{@inheritDoc} */
	@Override
	public UnitManagerReview updateUnitManagerReview(
			final UnitManagerReview unitManagerReview, 
			final StaffAssignment staffAssignment, final Date date, 
			final String text) throws DuplicateEntityFoundException {
		return this.unitManagerReviewDelegate.update(unitManagerReview, 
				staffAssignment, date, text, unitManagerReview.getOffender());
	}

	/**{@inheritDoc} */
	@Override
	public void removeUnitManagerReview(
			final UnitManagerReview unitManagerReview) {
		this.unitManagerReviewDelegate.remove(unitManagerReview);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date date, final String title, 
			final String filename, final String fileExtension) 
					throws DuplicateEntityFoundException {
		return this.documentDelegate.create(date, filename, fileExtension, 
				title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document, final Date date, 
			final String title) throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final String name, 
			final Document document) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag, 
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

	/**{@inheritDoc} */
	@Override
	public UnitManagerReviewDocumentAssociation 
			createUnitReviewDocumentAssociation(final Document document,
					final UnitManagerReview unitManagerReview) 
							throws DuplicateEntityFoundException {
		return this.unitManagerReviewDocumentAssociationDelegate.create(
				document, unitManagerReview);
	}

	/**{@inheritDoc} */
	@Override
	public void removeUnitManagerReviewDocumentAssociation(
			final UnitManagerReviewDocumentAssociation 
					unitManagerReviewDocumentAssociation) {
		this.unitManagerReviewDocumentAssociationDelegate.remove(
				unitManagerReviewDocumentAssociation);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<UnitManagerReviewDocumentAssociation> 
			findUnitManagerReviewDocumentAssociationsByUnitManagerReview(
					final UnitManagerReview unitManagerReview) {
		return this.unitManagerReviewDocumentAssociationDelegate
				.findByUnitManagerReview(unitManagerReview);
	}
}