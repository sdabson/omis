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
package omis.prisonterm.service;

import java.util.Date;
import java.util.List;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermDocumentAssociation;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.user.domain.UserAccount;

/**
 * Prison term service.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Dec 18, 2018)
 * @since OMIS 3.0
 */

public interface PrisonTermService {

	/**
	 * Creates a new prison term.
	 * 
	 * @param offender offender
	 * @param actionDate date
	 * @param preSentenceCredits integer
	 * @param sentenceDate date
	 * @param sentenceTermYears integer
	 * @param sentenceTermDays integer
	 * @param paroleEligibilityDate date
	 * @param projectedDischargeDate date
	 * @param maximumDischargeDate date
	 * @param status prison term status
	 * @param sentenceToFollow boolean
	 * @param comments text
	 * @param verificationUser Verification User
	 * @param verificationDate Verification Date
	 * @param sentenceCalculation Sentence Calculation
	 * @return new prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 * @throws ActivePrisonTermExistsException if an active prison term already
	 * exists.
	 */
	PrisonTerm create(Offender offender, Date actionDate, 
			Integer preSentenceCredits, Date sentenceDate, 
			Integer sentenceTermYears, Integer sentenceTermDays, 
			Date paroleEligibilityDate, Date projectedDischargeDate, 
			Date maximumDischargeDate, PrisonTermStatus status,  
			Boolean sentenceToFollow, String comments, 
			UserAccount verificationUser, Date verificationDate,
			PrisonTermDocumentAssociation sentenceCalculation)
		throws DuplicateEntityFoundException, ActivePrisonTermExistsException;
	
	/**
	 * Updates an existing prison term.
	 * 
	 * @param prisonTerm prison term
	 * @param actionDate date
	 * @param preSentenceCredits integer
	 * @param sentenceDate date
	 * @param sentenceTermYears integer
	 * @param sentenceTermDays integer
	 * @param paroleEligibilityDate date
	 * @param projectedDischargeDate date
	 * @param maximumDischargeDate date
	 * @param status prison term status
	 * @param sentenceToFollow boolean
	 * @param comments text
	 * @param verificationUser Verification User
	 * @param verificationDate Verification Date
	 * @param sentenceCalculation Sentence Calculation
	 * @return updated prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 * @throws ActivePrisonTermExistsException if an active prison term already
	 * exists.
	 */
	PrisonTerm update(PrisonTerm prisonTerm, Date actionDate, 
			Integer preSentenceCredits, Date sentenceDate, 
			Integer sentenceTermYears, Integer sentenceTermDays, 
			Date paroleEligibilityDate, Date projectedDischargeDate, 
			Date maximumDischargeDate, PrisonTermStatus status,  
			Boolean sentenceToFollow, String comments,
			UserAccount verificationUser, Date verificationDate,
			PrisonTermDocumentAssociation sentenceCalculation)
		throws DuplicateEntityFoundException, ActivePrisonTermExistsException;
	
	/**
	 * Creates a Prison Term Document Association with the specified Document
	 * and Prison Term.
	 * 
	 * @param document Document
	 * @param prisonTerm Prison Term
	 * @return Created Prison Term Document Association
	 * @throws DuplicateEntityFoundException - When a Prison Term Document
	 * Association already exists with the given Document and Prison Term.
	 */
	PrisonTermDocumentAssociation createPrisonTermDocumentAssociation(
			Document document, PrisonTerm prisonTerm)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Prison Term Document Association.
	 * 
	 * @param prisonTermDocumentAssociation Prison Term Document Association
	 */
	void removePrisonTermDocumentAssociation(
			PrisonTermDocumentAssociation prisonTermDocumentAssociation);
	
	/**
	 * Removes a prison term for the specified offender.
	 * 
	 * @param prisonTerm prison term
	 */	
	void remove(PrisonTerm prisonTerm);
	
	/**
	 * Returns a list of user accounts.
	 * 
	 * @param query Query
	 * @return list of user accounts
	 */
	List<UserAccount> findUserAccounts(String query);

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
}
