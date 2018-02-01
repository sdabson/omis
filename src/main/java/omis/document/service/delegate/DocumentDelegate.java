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

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.dao.DocumentDao;
import omis.document.domain.Document;
import omis.document.exception.DocumentExistsException;
import omis.instance.factory.InstanceFactory;

/** Service delegate for document.
 * @author Ryan Johns
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */ 
public class DocumentDelegate {
	private final DocumentDao documentDao;
	private final InstanceFactory<Document> documentInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	private static final String DUPLICATE_DOCUMENT_FOUND_MSG 
		= "Document already exists with given file name.";
	
	/** Constructor.
	 * @param documentDao - document dao.
	 * @param documentInstanceFactory - document instance factory. 
	 * @param auditComponentRetriever - audit component retriever. */
	public DocumentDelegate(final DocumentDao documentDao,
			final InstanceFactory<Document> documentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.documentDao = documentDao;
		this.documentInstanceFactory = documentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Create document.
	 * @param documentDate - date of document.
	 * @param filename - file name.
	 * @param fileExtension - file extension. 
	 * @param title - title. 
	 * @return document entity.
	 * @throws DocumentExistsException - when document with existing file
	 * name exists. */
	public Document create(final Date documentDate,
			final String filename, final String fileExtension,
			final String title) 
					throws DocumentExistsException {
		
		if (!(this.documentDao.findByFileName(filename).isEmpty())) {
			throw new DocumentExistsException(DUPLICATE_DOCUMENT_FOUND_MSG);
		}
		
		final Document document = this.documentInstanceFactory.createInstance();
		document.setDate(documentDate);
		document.setTitle(title);
		document.setFilename(filename);
		document.setFileExtension(fileExtension);
		document.setCreationSignature(this.retrieveCreationSignature());
		document.setUpdateSignature(this.retrieveUpdateSignature());
		return this.documentDao.makePersistent(document);
	}
	
	/** Updates document.
	 * @param document - document.
	 * @param title - title. 
	 * @param date - date. 
	 * @return document document.
	 * @throws DocumentExistsException */
	public Document update(final Document document, final String title, 
			final Date date) throws DocumentExistsException {
		
		if (!(this.documentDao.findByFileNameExcluding(document.getFilename(),
				document).isEmpty())){
			throw new DocumentExistsException(DUPLICATE_DOCUMENT_FOUND_MSG);
		}
		
		document.setTitle(title);
		document.setDate(date);
		document.setUpdateSignature(this.retrieveUpdateSignature());
		
		return this.documentDao.makePersistent(document);
	}
	
	/**
	 * Removes a specified document.
	 * @param document - Document to remove
	 */
	public void remove(final Document document){
		this.documentDao.makeTransient(document);
	}
	
	/* Retrieves creation Signature. */
	private CreationSignature retrieveCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	/* Retrieve update signature. */
	private UpdateSignature retrieveUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}