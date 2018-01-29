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
package omis.boardhearingdecision.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearingdecision.dao.HearingDecisionReasonDao;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for hearing decision reason.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionReasonDelegate {

	/* Data access objects. */
	
	private final HearingDecisionReasonDao hearingDecisionReasonDao;

	/* Instance factories. */
	
	private final InstanceFactory<HearingDecisionReason> 
			hearingDecisionReasonInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of hearing decision reason delegate with 
	 * the specified data access object, instance factory and audit component 
	 * retriever.
	 * 
	 * @param hearingDecisionReasonDao hearing decision reason data access 
	 * object
	 * @param hearingDecisionReasonInstanceFactory hearing decision reason 
	 * instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public HearingDecisionReasonDelegate(
			final HearingDecisionReasonDao hearingDecisionReasonDao,
			final InstanceFactory<HearingDecisionReason> 
					hearingDecisionReasonInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingDecisionReasonDao = hearingDecisionReasonDao;
		this.hearingDecisionReasonInstanceFactory = 
				hearingDecisionReasonInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new hearing decision reason with the specified reason name, 
	 * decision category and whether valid.
	 * 
	 * @param reasonName reason name
	 * @param category decision category
	 * @param valid valid
	 * @return hearing decision reason
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingDecisionReason create(final String reasonName, 
			final DecisionCategory category, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.hearingDecisionReasonDao.find(reasonName) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing decision reason already exists.");
		}
		HearingDecisionReason hearingDecisionReason = 
				this.hearingDecisionReasonInstanceFactory.createInstance();
		populateHearingDecisionReason(hearingDecisionReason, reasonName, 
				category, valid);
		hearingDecisionReason.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.hearingDecisionReasonDao.makePersistent(
				hearingDecisionReason);
	}
	
	/**
	 * Updates an existing hearing decision reason with the specified reason 
	 * name, decision category and whether valid.
	 * 
	 * @param hearingDecisionReason hearing decision reason
	 * @param reasonName reason name
	 * @param category decision category
	 * @param valid valid
	 * @return hearing decision reason
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingDecisionReason update(
			final HearingDecisionReason hearingDecisionReason, 
			final String reasonName, final DecisionCategory category, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.hearingDecisionReasonDao.findExcluding(reasonName, 
				hearingDecisionReason) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing decision reason already exists.");
		}
		populateHearingDecisionReason(hearingDecisionReason, reasonName, 
				category, valid);
		return this.hearingDecisionReasonDao.makePersistent(
				hearingDecisionReason);
	}

	/**
	 * Removes the specified hearing decision reason.
	 * 
	 * @param hearingDecisionReason hearing decision reason
	 */
	public void remove(final HearingDecisionReason hearingDecisionReason) {
		this.hearingDecisionReasonDao.makeTransient(hearingDecisionReason);
	}
	
	/**
	 * Returns a list of hearing decision reasons for the specified decision 
	 * category.
	 * 
	 * @param decisionCategory decision category
	 * @return list of hearing decision reasons
	 */
	public List<HearingDecisionReason> findByDecisionCategory(
			final DecisionCategory decisionCategory) {
		return this.hearingDecisionReasonDao.findByDecisionCategory(
				decisionCategory);
	}

	// Populates a hearing decision reason
	private void populateHearingDecisionReason(
			final HearingDecisionReason hearingDecisionReason,
			final String reasonName, final DecisionCategory category, 
			final Boolean valid) {
		hearingDecisionReason.setReasonName(reasonName);
		hearingDecisionReason.setCategory(category);
		hearingDecisionReason.setValid(valid);
		hearingDecisionReason.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
