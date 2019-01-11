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
package omis.probationparole.service.impl;

import java.util.Date;
import java.util.List;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.domain.ProbationParoleDocumentCategory;
import omis.probationparole.service.ProbationParoleDocumentService;
import omis.probationparole.service.delegate.ProbationParoleDocumentAssociationDelegate;
import omis.probationparole.service.delegate.ProbationParoleDocumentCategoryDelegate;

/**
 * Probation Parole Document Service Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 7, 2018)
 *@since OMIS 3.0
 *
 */
public class ProbationParoleDocumentServiceImpl
		implements ProbationParoleDocumentService {
	
	private final ProbationParoleDocumentAssociationDelegate
			probationParoleDocumentAssociationDelegate;
	
	private final ProbationParoleDocumentCategoryDelegate
			probationParoleDocumentCategoryDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	/**
	 * Constructor for ProbationParoleDocumentServiceImpl.
	 * 
	 * @param probationParoleDocumentAssociationDelegate - Probation Parole
	 * Document Association Delegate
	 * @param probationParoleDocumentCategoryDelegate - Probation Parole
	 * Document Category Delegate
	 * @param documentDelegate - Document Delegate
	 * @param documentTagDelegate - Document Tag Delegate
	 * @param docketDelegate - Docket Delegate
	 */
	public ProbationParoleDocumentServiceImpl(
			final ProbationParoleDocumentAssociationDelegate
					probationParoleDocumentAssociationDelegate,
			final ProbationParoleDocumentCategoryDelegate
					probationParoleDocumentCategoryDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		super();
		this.probationParoleDocumentAssociationDelegate =
				probationParoleDocumentAssociationDelegate;
		this.probationParoleDocumentCategoryDelegate =
				probationParoleDocumentCategoryDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public ProbationParoleDocumentAssociation
		createProbationParoleDocumentAssociation(final Document document,
				final Offender offender, final Date date,
			final ProbationParoleDocumentCategory category)
					throws DuplicateEntityFoundException {
		return this.probationParoleDocumentAssociationDelegate.create(
				document, offender, date, category);
	}

	/** {@inheritDoc} */
	@Override
	public ProbationParoleDocumentAssociation
				updateProbationParoleDocumentAssociation(
			final ProbationParoleDocumentAssociation
				probationParoleDocumentAssociation,
			final Document document,
			final Offender offender, final Date date,
			final ProbationParoleDocumentCategory category)
					throws DuplicateEntityFoundException {
		return this.probationParoleDocumentAssociationDelegate.update(
				probationParoleDocumentAssociation, document, offender,
				date, category);
	}

	/** {@inheritDoc} */
	@Override
	public void removeProbationParoleDocumentAssociation(
			final ProbationParoleDocumentAssociation
				probationParoleDocumentAssociation) {
		this.probationParoleDocumentAssociationDelegate.remove(
				probationParoleDocumentAssociation);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate,
			final String filename, final String fileExtension,
			final String title)
					throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename,
				fileExtension, title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document,
			final Date date, final String title)
					throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(
			final Document document, final String name)
					throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(
			final DocumentTag documentTag, final String name)
					throws DuplicateEntityFoundException {
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

	/** {@inheritDoc} */
	@Override
	public List<ProbationParoleDocumentCategory> findCategories() {
		return this.probationParoleDocumentCategoryDelegate.findCategories();
	}

}
