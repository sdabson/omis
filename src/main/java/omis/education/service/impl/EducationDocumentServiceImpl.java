package omis.education.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.education.domain.EducationAssociableDocument;
import omis.education.domain.EducationDocumentCategory;
import omis.education.service.EducationDocumentService;
import omis.education.service.delegate.EducationAssociableDocumentDelegate;
import omis.education.service.delegate.EducationDocumentCategoryDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * EducationDocumentServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationDocumentServiceImpl implements EducationDocumentService {
	
	private final EducationAssociableDocumentDelegate
		educationAssociableDocumentDelegate;
	
	private final EducationDocumentCategoryDelegate 
		educationDocumentCategoryDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	
	/**
	 * @param educationAssociableDocumentDelegate
	 * @param educationDocumentCategoryDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public EducationDocumentServiceImpl(
			final EducationAssociableDocumentDelegate
				educationAssociableDocumentDelegate,
			final EducationDocumentCategoryDelegate
				educationDocumentCategoryDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.educationAssociableDocumentDelegate =
				educationAssociableDocumentDelegate;
		this.educationDocumentCategoryDelegate =
				educationDocumentCategoryDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public EducationAssociableDocument create(final Offender offender,
			final Document document, final EducationDocumentCategory category)
			throws DuplicateEntityFoundException {
		return this.educationAssociableDocumentDelegate.create(
				offender, document, category);
	}

	/**{@inheritDoc} */
	@Override
	public EducationAssociableDocument update(
			final EducationAssociableDocument educationAssociableDocument,
			final Document document, final EducationDocumentCategory category)
					throws DuplicateEntityFoundException {
		return this.educationAssociableDocumentDelegate.update(
				educationAssociableDocument, document, category);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(
			final EducationAssociableDocument educationAssociableDocument) {
		this.educationAssociableDocumentDelegate.remove(
				educationAssociableDocument);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate,
			final String filename, final String fileExtension, final String title)
			throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename,
				fileExtension, title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document,
			final Date documentDate, final String title)
			throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, documentDate);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document,
			final String name) throws DuplicateEntityFoundException {
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
	public List<EducationDocumentCategory> findAllEducationDocumentCategories() {
		return this.educationDocumentCategoryDelegate.findAll();
	}

}
