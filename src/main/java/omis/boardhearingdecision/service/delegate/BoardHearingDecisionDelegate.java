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

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearingdecision.dao.BoardHearingDecisionDao;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for board hearing decision.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Mar 13, 2019)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionDelegate {

	/* Data access objects. */
	
	private final BoardHearingDecisionDao boardHearingDecisionDao;

	/* Instance factories. */
	
	private final InstanceFactory<BoardHearingDecision> 
			boardHearingDecisionInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of board hearing decision delegate with 
	 * the specified data access object, instance factory and audit component 
	 * retriever.
	 * 
	 * @param boardHearingDecisionDao board hearing decision data access object
	 * @param boardHearingDecisionInstanceFactory board hearing decision 
	 * instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public BoardHearingDecisionDelegate(
			final BoardHearingDecisionDao boardHearingDecisionDao,
			final InstanceFactory<BoardHearingDecision> 
					boardHearingDecisionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.boardHearingDecisionDao = boardHearingDecisionDao;
		this.boardHearingDecisionInstanceFactory = 
				boardHearingDecisionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a board hearing decision with the specified board hearing and 
	 * board hearing decision category.
	 * 
	 * @param hearing board hearing
	 * @param category board hearing decision category
	 * @param rulingDetails ruling details
	 * @return board hearing decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardHearingDecision create(final BoardHearing hearing, 
			final BoardHearingDecisionCategory category,
			final String rulingDetails) 
					throws DuplicateEntityFoundException {
		if (this.boardHearingDecisionDao.find(hearing) != null) {
			throw new DuplicateEntityFoundException(
					"Board hearing decision already exists.");
		}
		BoardHearingDecision boardDecision = 
				this.boardHearingDecisionInstanceFactory.createInstance();
		populateBoardHearingDecision(boardDecision, hearing, category,
				rulingDetails);
		boardDecision.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.boardHearingDecisionDao.makePersistent(boardDecision);
	}

	/**
	 * Updates a board hearing decision with the specified board hearing and 
	 * board hearing decision category.
	 * 
	 * @param boardDecision board hearing decision
	 * @param hearing board hearing
	 * @param category board hearing decision category
	 * @param rulingDetails ruling details
	 * @return board hearing decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardHearingDecision update(
			final BoardHearingDecision boardDecision, 
				final BoardHearing hearing, 
				final BoardHearingDecisionCategory category,
				final String rulingDetails) 
						throws DuplicateEntityFoundException {
		if (this.boardHearingDecisionDao.findExcluding(hearing, boardDecision) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Board hearing decision already exists.");
		}
		populateBoardHearingDecision(boardDecision, hearing, category,
				rulingDetails);
		return this.boardHearingDecisionDao.makePersistent(boardDecision);
	}

	/**
	 * Removes the specified board hearing decision.
	 * 
	 * @param boardDecision board hearing decision
	 */
	public void remove(final BoardHearingDecision boardDecision) {
		this.boardHearingDecisionDao.makeTransient(boardDecision);
	}

	/**
	 * Returns the board hearing decision for the specified board hearing.
	 * 
	 * @param hearing board hearing
	 * @return board hearing decision
	 */
	public BoardHearingDecision findByHearing(final BoardHearing hearing) {
		return this.boardHearingDecisionDao.find(hearing);
	}

	// Populates a board hearing decision
	private void populateBoardHearingDecision(
			final BoardHearingDecision boardDecision, 
			final BoardHearing hearing, 
			final BoardHearingDecisionCategory category,
			final String rulingDetails) {
		boardDecision.setHearing(hearing);
		boardDecision.setCategory(category);
		boardDecision.setRulingDetails(rulingDetails);
		boardDecision.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
