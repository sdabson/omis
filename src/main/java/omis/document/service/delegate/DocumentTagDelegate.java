/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.document.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.dao.DocumentTagDao;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.exception.DocumentTagExistsException;
import omis.instance.factory.InstanceFactory;

/** Service delegate for document tag.
 * @author Ryan Johns
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */
public class DocumentTagDelegate {
	private static final String DUPLICATE_DOCUMENT_TAG_FOUND_MSG = 
			"documentTag.exists";
	private final InstanceFactory<DocumentTag> documentTagInstanceFactory;
	private final DocumentTagDao documentTagDao;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor. 
	 * @param documentTagDao - document tag dao.
	 * @param documentTagInstanceFactory - document tag instance factory.
	 * @param auditComponentRetriever - audit component retriever. */
	public DocumentTagDelegate(final DocumentTagDao documentTagDao,
			final InstanceFactory<DocumentTag> documentTagInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.documentTagDao = documentTagDao;
		this.documentTagInstanceFactory = documentTagInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/** Tag document.
	 * @param document - document.
	 * @param name - tag name. 
	 * @return document tag. 
	 * @throws DocumentTagExistsException - when document is already tagged
	 * with given name. */
	public DocumentTag tagDocument(final Document document, 
			final String name) throws DocumentTagExistsException {
		
		if (this.documentTagDao.findByTagNameAndDocument(name, document) 
				!= null) {
			throw new DocumentTagExistsException(
					DUPLICATE_DOCUMENT_TAG_FOUND_MSG);
		}
		
		final DocumentTag documentTag = this.documentTagInstanceFactory
				.createInstance();
		documentTag.setDocument(document);
		documentTag.setName(name);
		documentTag.setCreationSignature(this.retrieveCreationSignature());
		documentTag.setUpdateSignature(this.retrieveUpdateSignature());
		return this.documentTagDao.makePersistent(documentTag);
	}
	
	/** Update tag.
	 * @param documentTag - document tag.
	 * @param name - name. 
	 * @return document tag. 
	 * @throws DocumentTagExistsException - when document is already tagged
	 * with given name. */
	public DocumentTag update(final DocumentTag documentTag, 
			final String name) 
		throws DocumentTagExistsException {
		if (this.documentTagDao.findByTagNameAndDocumentExcluding(name, 
				documentTag.getDocument(), documentTag) != null) {
			throw new DocumentTagExistsException(
					DUPLICATE_DOCUMENT_TAG_FOUND_MSG);
		}
		documentTag.setName(name);
		documentTag.setUpdateSignature(this.retrieveUpdateSignature());
		
		return this.documentTagDao.makePersistent(documentTag);
	}
	
	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	public List<DocumentTag> findByDocument(final Document document) {
		return this.documentTagDao.findByDocument(document);
	}
	
	/** Remove tag.
	 * @param documentTag - document tag. */
	public void removeTag(final DocumentTag documentTag) {
		this.documentTagDao.makeTransient(documentTag);
	}
	
	/* retrieve creation signature.
	 * @return creation signature. */
	private CreationSignature retrieveCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate());
	}
	
	/* retrieve update signature.
	 * @return update signature. */
	private UpdateSignature retrieveUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}