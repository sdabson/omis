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
package omis.unitmanagerreview.service;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;

/**
 * Service for unit manager review.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public interface UnitManagerReviewService {

	/**
	 * Creates a new unit manager review with the specified parameters.
	 * 
	 * @param staffAssignment staff assignment
	 * @param date date
	 * @param text text
	 * @param offender offender
	 * @return unit manager review
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	UnitManagerReview createUnitManagerReview(StaffAssignment staffAssignment, 
			Date date, String text, Offender offender) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing unit manager review with the specified parameters.
	 * 
	 * @param unitManagerReview unit manager review
	 * @param staffAssignment staff assignment
	 * @param date date
	 * @param text text
	 * @param offender offender
	 * @return unit manager review
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	UnitManagerReview updateUnitManagerReview(
			UnitManagerReview unitManagerReview, 
			StaffAssignment staffAssignment, Date date, String text) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 */
	void removeUnitManagerReview(UnitManagerReview unitManagerReview);
	
	/**
	 * Creates a new document.
	 * 
	 * @param date date
	 * @param title title
	 * @param filename filename
	 * @param fileExtension file extension
	 * @return document
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	Document createDocument(Date date, String title, String filename, 
			String fileExtension) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing document.
	 * 
	 * @param document document
	 * @param date date
	 * @param title title
	 * @return document
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	Document updateDocument(Document document, Date date, String title) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified document.
	 * 
	 * @param document document
	 */
	void removeDocument(Document document);
	
	/**
	 * Creates a new document tag.
	 * 
	 * @param name name
	 * @param document document
	 * @return document tag
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	DocumentTag createDocumentTag(String name, Document document) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing document tag.
	 * 
	 * @param documentTag document tag
	 * @param name name
	 * @return document tag
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	DocumentTag updateDocumentTag(DocumentTag documentTag, String name) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified document tag.
	 * 
	 * @param documentTag document tag
	 */
	void removeDocumentTag(DocumentTag documentTag);
	
	/**
	 * Returns a list of document tags for the specified document.
	 * 
	 * @param document document
	 * @return list of document tags
	 */
	List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Create a new unit manager review document association.
	 * 
	 * @param document document
	 * @param unitManagerReview unit manager review
	 * @return unit manager review document association
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	UnitManagerReviewDocumentAssociation createUnitReviewDocumentAssociation(
			Document document, UnitManagerReview unitManagerReview) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified unit manager review document association.
	 * 
	 * @param unitManagerReviewDocumentAssociation unit manager review document 
	 * association
	 */
	void removeUnitManagerReviewDocumentAssociation(
			UnitManagerReviewDocumentAssociation 
					unitManagerReviewDocumentAssociation);
	
	/**
	 * Returns a list of unit manager review document associations for the 
	 * specified unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 * @return list of unit manager review document associations
	 */
	List<UnitManagerReviewDocumentAssociation> 
			findUnitManagerReviewDocumentAssociationsByUnitManagerReview(
					UnitManagerReview unitManagerReview);
}
