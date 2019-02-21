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
package omis.earlyreleasetracking.service.impl;

import java.util.Date;
import java.util.List;
import omis.docket.domain.Docket;
import omis.docket.service.delegate.DocketDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
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
import omis.earlyreleasetracking.exception.EarlyReleaseRequestDocumentAssociationExists;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestExistsException;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestExternalOppositionExists;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestInternalApprovalExists;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestNoteAssociationExistsException;
import omis.earlyreleasetracking.service.EarlyReleaseRequestService;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseJudgeResponseCategoryDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseRequestDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseRequestDocumentAssociationDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseRequestExternalOppositionDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseRequestInternalApprovalDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseRequestNoteAssociationDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseStatusCategoryDelegate;
import omis.earlyreleasetracking.service.delegate.ExternalOppositionPartyCategoryDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Early Release Request Service Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestServiceImpl
		implements EarlyReleaseRequestService {
	
	private final EarlyReleaseRequestDelegate earlyReleaseRequestDelegate;
	
	private final EarlyReleaseRequestNoteAssociationDelegate
				earlyReleaseRequestNoteAssociationDelegate;
	
	private final EarlyReleaseRequestDocumentAssociationDelegate
				earlyReleaseRequestDocumentAssociationDelegate;
	
	private final EarlyReleaseRequestInternalApprovalDelegate
				earlyReleaseRequestInternalApprovalDelegate;
	
	private final EarlyReleaseRequestExternalOppositionDelegate
				earlyReleaseRequestExternalOppositionDelegate;
	
	private final EarlyReleaseJudgeResponseCategoryDelegate
				earlyReleaseJudgeResponseCategoryDelegate;
	
	private final EarlyReleaseStatusCategoryDelegate
				earlyReleaseStatusCategoryDelegate;
	
	private final ExternalOppositionPartyCategoryDelegate
				externalOppositionPartyCategoryDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	private final DocketDelegate docketDelegate;
	
	/**
	 * Constructor for EarlyReleaseRequestServiceImpl.
	 * 
	 * @param earlyReleaseRequestDelegate Early Release Request Delegate
	 * @param earlyReleaseRequestNoteAssociationDelegate Early Release Request
	 * Note Association Delegate
	 * @param earlyReleaseRequestDocumentAssociationDelegate Early Release
	 * Request Document Association Delegate
	 * @param earlyReleaseRequestInternalApprovalDelegate Early Release Request
	 * Internal Approval Delegate
	 * @param earlyReleaseRequestExternalOppositionDelegate Early Release
	 * Request External Opposition Delegate
	 * @param earlyReleaseJudgeResponseCategoryDelegate Early Release Judge
	 * Response Category Delegate
	 * @param earlyReleaseStatusCategoryDelegate Early Release Status Category
	 * Delegate
	 * @param externalOppositionPartyCategoryDelegate External Opposition Party
	 * Category Delegate
	 * @param documentDelegate Document Delegate
	 * @param documentTagDelegate Document Tag Delegate
	 * @param docketDelegate Docket Delegate
	 */
	public EarlyReleaseRequestServiceImpl(
			final EarlyReleaseRequestDelegate earlyReleaseRequestDelegate,
			final EarlyReleaseRequestNoteAssociationDelegate
				earlyReleaseRequestNoteAssociationDelegate,
			final EarlyReleaseRequestDocumentAssociationDelegate
				earlyReleaseRequestDocumentAssociationDelegate,
			final EarlyReleaseRequestInternalApprovalDelegate
				earlyReleaseRequestInternalApprovalDelegate,
			final EarlyReleaseRequestExternalOppositionDelegate
				earlyReleaseRequestExternalOppositionDelegate,
			final EarlyReleaseJudgeResponseCategoryDelegate
				earlyReleaseJudgeResponseCategoryDelegate,
			final EarlyReleaseStatusCategoryDelegate
				earlyReleaseStatusCategoryDelegate,
			final ExternalOppositionPartyCategoryDelegate
				externalOppositionPartyCategoryDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final DocketDelegate docketDelegate) {
		this.earlyReleaseRequestDelegate = earlyReleaseRequestDelegate;
		this.earlyReleaseRequestNoteAssociationDelegate =
				earlyReleaseRequestNoteAssociationDelegate;
		this.earlyReleaseRequestDocumentAssociationDelegate =
				earlyReleaseRequestDocumentAssociationDelegate;
		this.earlyReleaseRequestInternalApprovalDelegate =
				earlyReleaseRequestInternalApprovalDelegate;
		this.earlyReleaseRequestExternalOppositionDelegate =
				earlyReleaseRequestExternalOppositionDelegate;
		this.earlyReleaseJudgeResponseCategoryDelegate =
				earlyReleaseJudgeResponseCategoryDelegate;
		this.earlyReleaseStatusCategoryDelegate =
				earlyReleaseStatusCategoryDelegate;
		this.externalOppositionPartyCategoryDelegate =
				externalOppositionPartyCategoryDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.docketDelegate = docketDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequest createEarlyReleaseRequest(final Docket docket,
			final Date requestDate, final EarlyReleaseRequestCategory category,
			final EarlyReleaseStatusCategory requestStatus,
			final EarlyReleaseJudgeResponseCategory judgeResponse,
			final UserAccount requestBy, final Date approvalDate,
			final String comments)
					throws EarlyReleaseRequestExistsException {
		return this.earlyReleaseRequestDelegate.create(docket, requestDate,
				category, requestStatus, judgeResponse, requestBy, approvalDate,
				comments);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequest updateEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest, final Docket docket,
			final Date requestDate, final EarlyReleaseRequestCategory category,
			final EarlyReleaseStatusCategory requestStatus,
			final EarlyReleaseJudgeResponseCategory judgeResponse,
			final UserAccount requestBy, final Date approvalDate,
			final String comments)
					throws EarlyReleaseRequestExistsException {
		return this.earlyReleaseRequestDelegate.update(earlyReleaseRequest,
				docket, requestDate, category, requestStatus, judgeResponse,
				requestBy, approvalDate, comments);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		this.earlyReleaseRequestDelegate.remove(earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestNoteAssociation
			createEarlyReleaseRequestNoteAssociation(
					final EarlyReleaseRequest earlyReleaseRequest,
					final String description, final Date date)
					throws EarlyReleaseRequestNoteAssociationExistsException {
		return this.earlyReleaseRequestNoteAssociationDelegate.create(
				earlyReleaseRequest, description, date);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestNoteAssociation
			updateEarlyReleaseRequestNoteAssociation(
					final EarlyReleaseRequestNoteAssociation
						earlyReleaseRequestNoteAssociation,
					final EarlyReleaseRequest earlyReleaseRequest,
					final String description, final Date date)
					throws EarlyReleaseRequestNoteAssociationExistsException {
		return this.earlyReleaseRequestNoteAssociationDelegate.update(
				earlyReleaseRequestNoteAssociation, earlyReleaseRequest,
				description, date);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEarlyReleaseRequestNoteAssociation(
			final EarlyReleaseRequestNoteAssociation
				earlyReleaseRequestNoteAssociation) {
		this.earlyReleaseRequestNoteAssociationDelegate.remove(
				earlyReleaseRequestNoteAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestDocumentAssociation
			createEarlyReleaseRequestDocumentAssociation(
					final Document document,
					final EarlyReleaseRequest earlyReleaseRequest)
						throws EarlyReleaseRequestDocumentAssociationExists {
		return this.earlyReleaseRequestDocumentAssociationDelegate.create(
				document, earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestDocumentAssociation
			updateEarlyReleaseRequestDocumentAssociation(
					final EarlyReleaseRequestDocumentAssociation
						earlyReleaseRequestDocumentAssociation,
					final Document document,
					final EarlyReleaseRequest earlyReleaseRequest)
						throws EarlyReleaseRequestDocumentAssociationExists {
		return this.earlyReleaseRequestDocumentAssociationDelegate.update(
				earlyReleaseRequestDocumentAssociation, document,
				earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEarlyReleaseRequestDocumentAssociation(
			final EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociation) {
		this.earlyReleaseRequestDocumentAssociationDelegate.remove(
				earlyReleaseRequestDocumentAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestInternalApproval
			createEarlyReleaseRequestInternalApproval(
					final EarlyReleaseRequest earlyReleaseRequest,
					final String name, final Date date,
					final InternalApprovalDecisionCategory decision,
					final String narrative)
							throws EarlyReleaseRequestInternalApprovalExists {
		return this.earlyReleaseRequestInternalApprovalDelegate.create(
				earlyReleaseRequest, name, date, decision, narrative);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestInternalApproval
			updateEarlyReleaseRequestInternalApproval(
					final EarlyReleaseRequestInternalApproval
						earlyReleaseRequestInternalApproval,
					final EarlyReleaseRequest earlyReleaseRequest,
					final String name, final Date date,
					final InternalApprovalDecisionCategory decision,
					final String narrative)
							throws EarlyReleaseRequestInternalApprovalExists {
		return this.earlyReleaseRequestInternalApprovalDelegate.update(
				earlyReleaseRequestInternalApproval, earlyReleaseRequest, name,
				date, decision, narrative);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEarlyReleaseRequestInternalApproval(
			final EarlyReleaseRequestInternalApproval
				earlyReleaseRequestInternalApproval) {
		this.earlyReleaseRequestInternalApprovalDelegate.remove(
				earlyReleaseRequestInternalApproval);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestExternalOpposition
			createEarlyReleaseRequestExternalOpposition(
					final EarlyReleaseRequest earlyReleaseRequest,
					final ExternalOppositionPartyCategory party,
					final Date date, final String narrative)
							throws EarlyReleaseRequestExternalOppositionExists {
		return this.earlyReleaseRequestExternalOppositionDelegate.create(
				earlyReleaseRequest, party, date, narrative);
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestExternalOpposition
			updateEarlyReleaseRequestExternalOpposition(
					final EarlyReleaseRequestExternalOpposition
						earlyReleaseRequestExternalOpposition,
					final EarlyReleaseRequest earlyReleaseRequest,
					final ExternalOppositionPartyCategory party,
					final Date date, final String narrative)
							throws EarlyReleaseRequestExternalOppositionExists {
		return this.earlyReleaseRequestExternalOppositionDelegate.update(
				earlyReleaseRequestExternalOpposition, earlyReleaseRequest,
				party, date, narrative);
	}

	/** {@inheritDoc} */
	@Override
	public void removeEarlyReleaseRequestExternalOpposition(
			final EarlyReleaseRequestExternalOpposition
				earlyReleaseRequestExternalOpposition) {
		this.earlyReleaseRequestExternalOppositionDelegate.remove(
				earlyReleaseRequestExternalOpposition);
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
	public List<EarlyReleaseRequestNoteAssociation>
			findEarlyReleaseRequestNoteAssociationsByEarlyReleaseRequest(
					final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestNoteAssociationDelegate
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestDocumentAssociation>
			findEarlyReleaseRequestDocumentAssociationsByEarlyReleaseRequest(
					final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestDocumentAssociationDelegate
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestInternalApproval>
			findEarlyReleaseRequestInternalApprovalsByEarlyReleaseRequest(
					final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestInternalApprovalDelegate
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestExternalOpposition>
			findEarlyReleaseRequestExternalOppositionsByEarlyReleaseRequest(
					final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestExternalOppositionDelegate
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<ExternalOppositionPartyCategory>
			findExternalOppositionPartyCategories() {
		return this.externalOppositionPartyCategoryDelegate.findAllCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseStatusCategory> findEarlyReleaseStatusCategories() {
		return this.earlyReleaseStatusCategoryDelegate.findAllCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseJudgeResponseCategory>
			findEarlyReleaseJudgeResponseCategories() {
		return this.earlyReleaseJudgeResponseCategoryDelegate
				.findAllCategories();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Docket> findDocketsByOffender(final Offender offender) {
		return this.docketDelegate.findByPerson(offender);
	}
}
