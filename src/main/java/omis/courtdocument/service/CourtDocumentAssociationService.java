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
package omis.courtdocument.service;

import java.util.Date;
import java.util.List;

import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/** 
 * Service for court case document related operations.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Jan 9, 2019)
 * @since OMIS 3.0
 */
public interface CourtDocumentAssociationService {
	
	/** 
	 * Create a court document association.
	 * 
	 * @param docket docket
	 * @param offender offender
	 * @param document document
	 * @param date date
	 * @param category court document category
	 * @return court document association
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	CourtDocumentAssociation createCourtDocumentAssociation(
			Docket docket, Offender offender, Document document, Date date, 
			CourtDocumentCategory category) 
					throws DuplicateEntityFoundException;
	
	/** 
	 * Updates a court document association.
	 * 
	 * @param courtDocumentAssociation court document association
	 * @param docket docket
	 * @param document document
	 * @param date date
	 * @param courtDocumentCategory court case document category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	CourtDocumentAssociation updateCourtDocumentAssociation(
			CourtDocumentAssociation courtDocumentAssociation, 
			Docket docket, Document document, Date date, 
			CourtDocumentCategory courtDocumentCategory) 
					throws DuplicateEntityFoundException;
	
	/** 
	 * Removes court document association.
	 * 
	 * @param courtDocumentAssociation court document association
	 */
	void removeCourtDocumentAssociation(
			CourtDocumentAssociation courtDocumentAssociation);
	
	/** Creates document.
	 * @param date - date.
	 * @param filename - filename.
	 * @param fileExtension - file extension.
	 * @param title - title. 
	 * @return document.
	 * @throws DuplicateEntityFoundException - when document with given title
	 * exists. */
	Document createDocument(
			Date date, String filename, String fileExtension, String title)
				throws DuplicateEntityFoundException;

	/** Updates document.
	 * @param document - document.
	 * @param title - title.
	 * @param date - date. 
	 * @throws DuplicateEntityFoundException */
	void updateDocument(Document document, String title, Date date)
			throws DuplicateEntityFoundException;
	
	/** Creates document tag.
	 * @param document - document.
	 * @param name - name.
	 * @return document tag.
	 * @throws DuplicateEntityFoundException - when document exists with given
	 * tag name. */
	DocumentTag createDocumentTag(Document document, String name) 
		throws DuplicateEntityFoundException;
	
	/** Updates document tag.
	 * @param documentTag - document tag.
	 * @param name - name.
	 * @throws DuplicateEntityFoundException - when document is already 
	 * tagged with given name. */
	void updateDocumentTag(DocumentTag documentTag, String name)
		throws DuplicateEntityFoundException;
	
	/** Removes document tag.
	 * @param documentTag - document tag. */
	void removeTag(DocumentTag documentTag);
	
	/** Finds document tag by document.
	 * @param document - document.
	 * @return document tags. */
	List<DocumentTag> findDocumentTagsByDocument(final Document document);
	
	/** Finds court document categories.
	 * @return list of court document categories. */
	List<CourtDocumentCategory> findCategories();
	
	/** 
	 * Returns a list of dockets for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of dockets
	 */
	List<Docket> findDocketsByOffender(Offender offender);

	/**
	 * Removes the specified document.
	 * 
	 * @param document document
	 */
	void removeDocument(Document document);
}