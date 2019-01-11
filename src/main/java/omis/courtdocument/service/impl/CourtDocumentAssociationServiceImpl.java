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
package omis.courtdocument.service.impl;

import java.util.Date;
import java.util.List;

import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.service.CourtDocumentAssociationService;
import omis.courtdocument.service.delegate.CourtDocumentAssociationDelegate;
import omis.courtdocument.service.delegate.CourtDocumentCategoryDelegate;
import omis.docket.domain.Docket;
import omis.docket.service.delegate.DocketDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/** 
 * Implementation of court document association service.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Jan 9, 2019)
 * @since OMIS 3.0
 */
public class CourtDocumentAssociationServiceImpl 
	implements CourtDocumentAssociationService {
	
	private final CourtDocumentAssociationDelegate 
		courtDocumentAssociationDelegate;
	
	private final DocketDelegate docketDelegate;
	
	private final CourtDocumentCategoryDelegate courtDocumentCategoryDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	/** 
	 * Constructor.
	 * 
	 * @param courtDocumentAssociationDelegate court document association 
	 * delegate
	 * @param courtDocumentCategoryDelegate court document category delegate
	 * @param documentDelegate document delegate
	 * @param documentTagDelegate document tag delegate
	 * @param docketDelegate docket delegate 
	 */
	public CourtDocumentAssociationServiceImpl(
			final CourtDocumentAssociationDelegate 
				courtDocumentAssociationDelegate, 
				final CourtDocumentCategoryDelegate 
				courtDocumentCategoryDelegate, 
				final DocumentDelegate documentDelegate, 
				final DocumentTagDelegate documentTagDelegate,
				final DocketDelegate docketDelegate) {
		this.courtDocumentAssociationDelegate = 
				courtDocumentAssociationDelegate;
		this.courtDocumentCategoryDelegate = courtDocumentCategoryDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.docketDelegate = docketDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtDocumentAssociation createCourtDocumentAssociation(
			final Docket docket, final Offender offender, 
			final Document document, final Date date,
			final CourtDocumentCategory category) 
					throws DuplicateEntityFoundException {
		return this.courtDocumentAssociationDelegate.create(docket, 
				offender, document, date, category);
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtDocumentAssociation updateCourtDocumentAssociation(
			final CourtDocumentAssociation courtDocumentAssociation, 
			final Docket docket, final Document document, 
			final Date date, final CourtDocumentCategory courtDocumentCategory) 
					throws DuplicateEntityFoundException { 
		return this.courtDocumentAssociationDelegate.update(
				courtDocumentAssociation, docket, 
				courtDocumentAssociation.getOffender(), document, date, 
				courtDocumentCategory);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeCourtDocumentAssociation(
			final CourtDocumentAssociation courtDocumentAssociation) {
		this.courtDocumentAssociationDelegate.remove(courtDocumentAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(
			final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Docket> findDocketsByOffender(
			final Offender offender) {
		return this.docketDelegate.findByPerson(offender);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtDocumentCategory> findCategories() {
		return this.courtDocumentCategoryDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public Document createDocument(final Date date, final String filename,
			final String fileExtension, final String title)
					throws DuplicateEntityFoundException {
		return this.documentDelegate.create(date, filename,	fileExtension, 
				title);
	}
	
	/** {@inheritDoc} */
	@Override
	public void updateDocumentTag(final DocumentTag documentTag, 
			final String name) throws DuplicateEntityFoundException {
		this.documentTagDelegate.update(documentTag, name);
	}
	
	/** {@inheritDoc} 
	 * @throws DuplicateEntityFoundException */
	@Override
	public void updateDocument(final Document document, final String title,
			final Date date) throws DuplicateEntityFoundException {
		this.documentDelegate.update(document, title, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}
	
	/** {@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document, 
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}
}