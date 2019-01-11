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
package omis.probationparole.service;

import java.util.Date;
import java.util.List;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.domain.ProbationParoleDocumentCategory;

/**
 * Probation Parole Document Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 6, 2018)
 *@since OMIS 3.0
 *
 */
public interface ProbationParoleDocumentService {
	
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
	ProbationParoleDocumentAssociation createProbationParoleDocumentAssociation(
			Document document, Offender offender, Date date,
			ProbationParoleDocumentCategory category)
					throws DuplicateEntityFoundException;
	
	
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
	ProbationParoleDocumentAssociation updateProbationParoleDocumentAssociation(
			ProbationParoleDocumentAssociation
				probationParoleDocumentAssociation, Document document,
			Offender offender, Date date,
			ProbationParoleDocumentCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Probation Parole Document Association.
	 * 
	 * @param probationParoleDocumentAssociation - Probation Parole Document
	 * Association to remove.
	 */
	void removeProbationParoleDocumentAssociation(
			ProbationParoleDocumentAssociation
				probationParoleDocumentAssociation);
	
	/**
	 * Create document.
	 * 
	 * @param documentDate - date of document.
	 * @param filename - file name.
	 * @param fileExtension - file extension. 
	 * @param title - title. 
	 * @return document entity.
	 * @throws DuplicateEntityFoundException - when document with existing file
	 * name exists. */
	Document createDocument(Date documentDate,
			String filename, String fileExtension, String title)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates document.
	 * 
	 * @param document - Document
	 * @param documentDate - Date
	 * @param title - String Title
	 * @return Document - updated document
	 * @throws DuplicateEntityFoundException */
	Document updateDocument(Document document, Date documentDate,
			String title)
					throws DuplicateEntityFoundException;

	/**
	 * Removes a specified document.
	 * 
	 * @param document - Document to remove
	 */
	void removeDocument(Document document);

	/**
	 * Tag document.
	 * 
	 * @param document - document.
	 * @param name - tag name. 
	 * @return document tag. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	DocumentTag createDocumentTag(Document document, String name)
			throws DuplicateEntityFoundException;

	/**
	 * Update tag.
	 * 
	 * @param documentTag - document tag.
	 * @param name - name. 
	 * @return DocumentTag - updated Document Tag.
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	DocumentTag updateDocumentTag(DocumentTag documentTag, String name)
			throws DuplicateEntityFoundException;

	/**
	 * Remove tag.
	 * 
	 * @param documentTag - document tag. */
	void removeDocumentTag(DocumentTag documentTag);
	

	/**
	 * Finds document tags by document.
	 * 
	 * @param document - document.
	 * @return list of document tags. */
	List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Returns a list of all valid Probation Parole Document Categories.
	 * 
	 * @return List of all valid Probation Parole Document Categories.
	 */
	List<ProbationParoleDocumentCategory> findCategories();
	
}
