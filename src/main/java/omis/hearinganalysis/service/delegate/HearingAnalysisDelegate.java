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
package omis.hearinganalysis.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.dao.HearingAnalysisDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.instance.factory.InstanceFactory;
import omis.paroleboarditinerary.domain.BoardAttendee;
import omis.paroleboarditinerary.domain.BoardMeetingSite;
import omis.paroleeligibility.domain.ParoleEligibility;

/**
 * Hearing analysis delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Dec 18, 2017)
 * @since OMIS 3.0
 */
public class HearingAnalysisDelegate {

	/* Data access objects. */
	
	private final HearingAnalysisDao hearingAnalysisDao;

	/* Instance factories. */
	
	private final InstanceFactory<HearingAnalysis> 
			hearingAnalysisInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of hearing analysis delegate with the 
	 * specified data access object and instance factory.
	 * 
	 * @param hearingAnalysisDao hearing analysis data access object
	 * @param hearingAnalysisInstanceFactory hearing analysis instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public HearingAnalysisDelegate(final HearingAnalysisDao hearingAnalysisDao,
			final InstanceFactory<HearingAnalysis> 
				hearingAnalysisInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingAnalysisDao = hearingAnalysisDao;
		this.hearingAnalysisInstanceFactory = hearingAnalysisInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new hearing analysis.
	 * 
	 * @param eligibility parole eligibility
	 * @param meetingSite board meeting site
	 * @param analyst board attendee
	 * @return hearing analysis
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingAnalysis create(final ParoleEligibility eligibility, 
			final BoardMeetingSite meetingSite, 
			final HearingAnalysisCategory category, final BoardAttendee analyst) 
					throws DuplicateEntityFoundException {
		if (this.hearingAnalysisDao.find(eligibility, meetingSite, category,
				analyst) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing analyis already exists");
		}
		HearingAnalysis hearingAnalysis = this.hearingAnalysisInstanceFactory
				.createInstance();
		hearingAnalysis.setEligibility(eligibility);
		hearingAnalysis.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		populateHearingAnalysis(hearingAnalysis, meetingSite, category,
				analyst);
		return this.hearingAnalysisDao.makePersistent(hearingAnalysis);
	}

	/**
	 * Updates an existing hearing analysis.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @param meetingSite board meeting site
	 * @param analyst board attendee
	 * @return hearing analysis
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingAnalysis update(final HearingAnalysis hearingAnalysis, 
			final BoardMeetingSite meetingSite, 
			final HearingAnalysisCategory category, final BoardAttendee analyst) 
					throws DuplicateEntityFoundException {
		if (this.hearingAnalysisDao.findExcluding(
				hearingAnalysis.getEligibility(), meetingSite, category, 
				analyst, hearingAnalysis) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing analyis already exists");
		}
		populateHearingAnalysis(hearingAnalysis, meetingSite, category,
				analyst);
		return this.hearingAnalysisDao.makePersistent(hearingAnalysis);
	}

	/**
	 * Removes a hearing analysis.
	 * 
	 * @param hearingAnalysis hearing analysis
	 */
	public void remove(final HearingAnalysis hearingAnalysis) {
		this.hearingAnalysisDao.makeTransient(hearingAnalysis);
	}
	
	/**
	 * Returns the hearing analysis for the specified parole eligibility.
	 * 
	 * @param eligibility parole eligibility
	 * @return hearing analysis
	 */
	public HearingAnalysis findByParoleEligibility(
			final ParoleEligibility eligibility) {
		return this.hearingAnalysisDao.findByParoleEligibility(eligibility);
	}

	// Populates a hearing analysis
	private void populateHearingAnalysis(final HearingAnalysis hearingAnalysis, 
			final BoardMeetingSite meetingSite, 
			final HearingAnalysisCategory category, final BoardAttendee analyst) {
		hearingAnalysis.setBoardMeetingSite(meetingSite);
		hearingAnalysis.setAnalyst(analyst);
		hearingAnalysis.setCategory(category);
		hearingAnalysis.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
