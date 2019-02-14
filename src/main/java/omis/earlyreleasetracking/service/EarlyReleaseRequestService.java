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
package omis.earlyreleasetracking.service;

import java.util.Date;
import java.util.List;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestExternalOpposition;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestInternalApproval;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestNoteAssociation;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;
import omis.earlyreleasetracking.domain.InternalApprovalDecisionCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.user.domain.UserAccount;

/**
 * Early Release Request Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 11, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestService {
	
	/**
	 * Creates an Early Release Request with the given properties.
	 * 
	 * @param docket Docket
	 * @param requestDate Request Date
	 * @param category Category
	 * @param requestStatus Request Status
	 * @param judgeResponse Judge Response
	 * @param requestBy Request By
	 * @param approvalDate Approval Date
	 * @param comments Comments
	 * @return Newly created Early Release Request
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * already exists with the given Docket and Request Date
	 */
	EarlyReleaseRequest createEarlyReleaseRequest(Docket docket,
			Date requestDate, EarlyReleaseRequestCategory category,
			EarlyReleaseStatusCategory requestStatus,
			EarlyReleaseJudgeResponseCategory judgeResponse,
			UserAccount requestBy, Date approvalDate,
			String comments)
					throws DuplicateEntityFoundException;
	
	/**
	 * Update the specified Early Release Request with the given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request to update
	 * @param docket Docket
	 * @param requestDate Request Date
	 * @param category Category
	 * @param requestStatus Request Status
	 * @param judgeResponse Judge Response
	 * @param requestBy Request By
	 * @param approvalDate Approval Date
	 * @param comments Comments
	 * @return Updated Early Release Request
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * already exists with the given Docket and Request Date
	 */
	EarlyReleaseRequest updateEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest, Docket docket,
			Date requestDate, EarlyReleaseRequestCategory category,
			EarlyReleaseStatusCategory requestStatus,
			EarlyReleaseJudgeResponseCategory judgeResponse,
			UserAccount requestBy, Date approvalDate,
			String comments)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request to remove
	 */
	void removeEarlyReleaseRequest(EarlyReleaseRequest earlyReleaseRequest);
	
	/**
	 * Creates an Early Release Request Note Association with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param description Description
	 * @param date Date
	 * @return Newly created Early Release Request Note Association
	 * @throws DuplicateEntityFoundException When a Early Release Request Note
	 * Association already exists with the given Date, description, and
	 * Early Release Request
	 */
	EarlyReleaseRequestNoteAssociation createEarlyReleaseRequestNoteAssociation(
			EarlyReleaseRequest earlyReleaseRequest,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified Early Release Request Note Association with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequestNoteAssociation Early Release Request Note
	 * Association to update
	 * @param earlyReleaseRequest Early Release Request
	 * @param description Description
	 * @param date Date
	 * @return Updated Early Release Request Note Association
	 * @throws DuplicateEntityFoundException When a Early Release Request Note
	 * Association already exists with the given Date, description, and
	 * Early Release Request
	 */
	EarlyReleaseRequestNoteAssociation updateEarlyReleaseRequestNoteAssociation(
			EarlyReleaseRequestNoteAssociation
				earlyReleaseRequestNoteAssociation,
			EarlyReleaseRequest earlyReleaseRequest,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Early Release Request Note Association.
	 * 
	 * @param earlyReleaseRequestNoteAssociation Early Release Request Note
	 * Association to remove
	 */
	void removeEarlyReleaseRequestNoteAssociation(
			EarlyReleaseRequestNoteAssociation
				earlyReleaseRequestNoteAssociation);
	
	/**
	 * Creates a Early Release Request Document Association with
	 * the given properties.
	 * 
	 * @param document Document
	 * @param earlyReleaseRequest Early Release Request
	 * @return Newly created Early Release Request Document Association
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * Document Association already exists with the given Document and
	 * Early Release Request.
	 */
	EarlyReleaseRequestDocumentAssociation
		createEarlyReleaseRequestDocumentAssociation(Document document,
			EarlyReleaseRequest earlyReleaseRequest)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified Early Release Request Document Association with
	 * the given properties.
	 * 
	 * @param earlyReleaseRequestDocumentAssociation Early Release Request
	 * Document Association to update
	 * @param document Document
	 * @param earlyReleaseRequest Early Release Request
	 * @return Updated Early Release Request Document Association
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * Document Association already exists with the given Document and
	 * Early Release Request.
	 */
	EarlyReleaseRequestDocumentAssociation
		updateEarlyReleaseRequestDocumentAssociation(
			EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociation, Document document,
			EarlyReleaseRequest earlyReleaseRequest)
						throws DuplicateEntityFoundException;
	
	/**
	 * Removes the given Early Release Request Document Association.
	 * 
	 * @param earlyReleaseRequestDocumentAssociation Early Release Request
	 * Document Association to remove
	 */
	void removeEarlyReleaseRequestDocumentAssociation(
			EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociation);
	
	/**
	 * Creates an Early Release Request Internal Approval with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param name Name
	 * @param date Date
	 * @param decision Decision
	 * @param narrative Narrative
	 * @return Newly created Early Release Request Internal Approval
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * Internal Approval already exists with the given Name and Early Release
	 * Request.
	 */
	EarlyReleaseRequestInternalApproval
		createEarlyReleaseRequestInternalApproval(
			EarlyReleaseRequest earlyReleaseRequest,
			String name, Date date,
			InternalApprovalDecisionCategory decision,
			String narrative)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified Early Release Request Internal Approval with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequestInternalApproval Early Release Request Internal
	 * Approval to update
	 * @param earlyReleaseRequest Early Release Request
	 * @param name Name
	 * @param date Date
	 * @param decision Decision
	 * @param narrative Narrative
	 * @return Updated Early Release Request Internal Approval
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * Internal Approval already exists with the given Name and Early Release
	 * Request.
	 */
	EarlyReleaseRequestInternalApproval
		updateEarlyReleaseRequestInternalApproval(
			EarlyReleaseRequestInternalApproval
				earlyReleaseRequestInternalApproval,
			EarlyReleaseRequest earlyReleaseRequest,
			String name, Date date,
			InternalApprovalDecisionCategory decision,
			String narrative)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Early Release Request Internal Approval.
	 * 
	 * @param earlyReleaseRequestInternalApproval Early Release Request Internal
	 * Approval to remove
	 */
	void removeEarlyReleaseRequestInternalApproval(
			EarlyReleaseRequestInternalApproval
				earlyReleaseRequestInternalApproval);
	
	/**
	 * Creates a new Early Release Request External Opposition with
	 * the given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param party Party
	 * @param date Date
	 * @param narrative Narrative
	 * @return Newly created Early Release Request External Opposition
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * External Opposition already exists with the given party for the specified
	 * Early Release Request
	 */
	EarlyReleaseRequestExternalOpposition
		createEarlyReleaseRequestExternalOpposition(
			EarlyReleaseRequest earlyReleaseRequest,
			ExternalOppositionPartyCategory party, Date date,
			String narrative)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified Early Release Request External Opposition with
	 * the given properties.
	 * 
	 * @param earlyReleaseRequestExternalOpposition Early Release Request
	 * External Opposition to update
	 * @param earlyReleaseRequest Early Release Request
	 * @param party Party
	 * @param date Date
	 * @param narrative Narrative
	 * @return Updated Early Release Request External Opposition
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * External Opposition already exists with the given party for the specified
	 * Early Release Request
	 */
	EarlyReleaseRequestExternalOpposition
		updateEarlyReleaseRequestExternalOpposition(
			EarlyReleaseRequestExternalOpposition
				earlyReleaseRequestExternalOpposition,
			EarlyReleaseRequest earlyReleaseRequest,
			ExternalOppositionPartyCategory party, Date date,
			String narrative)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Early Release Request External Opposition.
	 * 
	 * @param earlyReleaseRequestExternalOpposition Early Release Request
	 * External Opposition to remove
	 */
	void removeEarlyReleaseRequestExternalOpposition(
			EarlyReleaseRequestExternalOpposition
				earlyReleaseRequestExternalOpposition);
	
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
	 * Returns a list of Early Release Request Note Associations by the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Note Associations by the
	 * specified Early Release Request.
	 */
	List<EarlyReleaseRequestNoteAssociation>
		findEarlyReleaseRequestNoteAssociationsByEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest);
	
	/**
	 * Returns a list of Early Release Request Document Associations for the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Document Associations for the
	 * specified Early Release Request.
	 */
	List<EarlyReleaseRequestDocumentAssociation>
		findEarlyReleaseRequestDocumentAssociationsByEarlyReleaseRequest(
						EarlyReleaseRequest earlyReleaseRequest);
	
	/**
	 * Returns a list ofEarly Release Request Internal Approvals by the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Internal Approvals by the specified
	 * Early Release Request.
	 */
	List<EarlyReleaseRequestInternalApproval>
		findEarlyReleaseRequestInternalApprovalsByEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest);
	
	/**
	 * Returns a list of Early Release Request External Oppositions for the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request External Oppositions for the
	 * specified Early Release Request.
	 */
	List<EarlyReleaseRequestExternalOpposition>
				findEarlyReleaseRequestExternalOppositionsByEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest);
	
	/**
	 * Returns a list of all valid External Opposition Party Categories.
	 * 
	 * @return List of all valid External Opposition Party Categories.
	 */
	List<ExternalOppositionPartyCategory>
		findExternalOppositionPartyCategories();
	
	/**
	 * Returns a list of all valid Early Release Status Categories.
	 * 
	 * @return List of all valid Early Release Status Categories.
	 */
	List<EarlyReleaseStatusCategory> findEarlyReleaseStatusCategories();
	
	/**
	 * Returns a list of all valid Early Release Judge Response Categories.
	 * 
	 * @return List of all valid Early Release Judge Response Categories.
	 */
	List<EarlyReleaseJudgeResponseCategory>
		findEarlyReleaseJudgeResponseCategories();
}
