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
package omis.prisonterm.service.impl;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermDocumentAssociation;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.prisonterm.service.PrisonTermService;
import omis.prisonterm.service.delegate.PrisonTermDelegate;
import omis.prisonterm.service.delegate.PrisonTermDocumentAssociationDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of service for prison terms.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Yidong Li
 * @author Annie Wahl
 * @version 0.1.2 (Dec 18, 2018)
 * @since OMIS 3.0
 */

public class PrisonTermServiceImpl implements PrisonTermService {

	private final PrisonTermDelegate prisonTermDelegate;
	
	private final PrisonTermDocumentAssociationDelegate
		prisonTermDocumentAssociationDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final DocumentDelegate documentDelegate;
	
	private final DocumentTagDelegate documentTagDelegate;
	
	
	/**
	 * Constructor for PrisonTermServiceImpl.
	 * 
	 * @param prisonTermDelegate prison term delegate
	 * @param prisonTermDocumentAssociationDelegate prison term document
	 * association delegate
	 * @param userAccountDelegate user account delegate
	 * @param documentDelegate document delegate
	 * @param documentTagDelegate document tag delegate
	 */
	public PrisonTermServiceImpl(
			final PrisonTermDelegate prisonTermDelegate,
			final PrisonTermDocumentAssociationDelegate
				prisonTermDocumentAssociationDelegate,
			final UserAccountDelegate userAccountDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate) {
		this.prisonTermDelegate = prisonTermDelegate;
		this.prisonTermDocumentAssociationDelegate =
				prisonTermDocumentAssociationDelegate;
		this.userAccountDelegate = userAccountDelegate;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTerm create(final Offender offender, final Date actionDate, 
			final Integer preSentenceCredits, final Date sentenceDate, 
			final Integer sentenceTermYears, final Integer sentenceTermDays,
			final Date paroleEligibilityDate, final Date projectedDischargeDate,
			final Date maximumDischargeDate, final PrisonTermStatus status,
			final Boolean sentenceToFollow, final String comments,
			final UserAccount verificationUser, final Date verificationDate,
			final PrisonTermDocumentAssociation sentenceCalculation)
					throws DuplicateEntityFoundException,
						ActivePrisonTermExistsException {
		PrisonTerm existingPrisonTerm = this.prisonTermDelegate
				.findActiveByOffender(offender);
		if (existingPrisonTerm != null && PrisonTermStatus.ACTIVE
				.equals(status)) {
			this.update(existingPrisonTerm, existingPrisonTerm.getActionDate(), 
				existingPrisonTerm.getPreSentenceCredits(), 
				existingPrisonTerm.getSentenceDate(), 
				existingPrisonTerm.getSentenceTermYears(), 
				existingPrisonTerm.getSentenceTermDays(), 
				existingPrisonTerm.getParoleEligibilityDate(), 
				existingPrisonTerm.getProjectedDischargeDate(), 
				existingPrisonTerm.getMaximumDischargeDate(), 
				PrisonTermStatus.HISTORICAL, 
				existingPrisonTerm.getSentenceToFollow(), 
				existingPrisonTerm.getComments(), 
				existingPrisonTerm.getVerificationUser(), 
				existingPrisonTerm.getVerificationDate(),
				existingPrisonTerm.getSentenceCalculation());
		}
		return this.prisonTermDelegate.create(offender, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate,
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate, sentenceCalculation);
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTerm update(final PrisonTerm prisonTerm, final Date actionDate,
			final Integer preSentenceCredits, final Date sentenceDate, 
			final Integer sentenceTermYears, final Integer sentenceTermDays,
			final Date paroleEligibilityDate, final Date projectedDischargeDate,
			final Date maximumDischargeDate, final PrisonTermStatus status,
			final Boolean sentenceToFollow, final String comments, 
			final UserAccount verificationUser, final Date verificationDate,
			final PrisonTermDocumentAssociation sentenceCalculation)
			throws DuplicateEntityFoundException, 
					ActivePrisonTermExistsException {
		return this.prisonTermDelegate.update(prisonTerm, actionDate,  
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, 
				projectedDischargeDate, maximumDischargeDate, status,  
				sentenceToFollow, comments, verificationUser, verificationDate,
				sentenceCalculation);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final PrisonTerm prisonTerm) {
		this.prisonTermDelegate.remove(prisonTerm);
	}
	
	/** {@inheritDoc} */
	@Override
	public PrisonTermDocumentAssociation createPrisonTermDocumentAssociation(
			final Document document, final PrisonTerm prisonTerm)
					throws DuplicateEntityFoundException {
		return this.prisonTermDocumentAssociationDelegate
					.create(document, prisonTerm);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removePrisonTermDocumentAssociation(
			final PrisonTermDocumentAssociation prisonTermDocumentAssociation) {
		this.prisonTermDocumentAssociationDelegate
					.remove(prisonTermDocumentAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findUserAccounts(final String query) {
		return this.userAccountDelegate.search(query);
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
}
