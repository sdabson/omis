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
package omis.hearingparticipant.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearing;
import omis.exception.DuplicateEntityFoundException;
import omis.hearingparticipant.dao.HearingParticipantDao;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/**
 * Hearing Participant Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Hearing Participent already exists with the given category for "
			+ "the specified person.";
	
	private final HearingParticipantDao hearingParticipantDao;
	
	private final InstanceFactory<HearingParticipant> 
		hearingParticipantInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for HearingParticipantDelegate.
	 * @param hearingParticipantDao - Hearing Participant Dao
	 * @param hearingParticipantInstanceFactory - Hearing Participant Instance
	 * Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public HearingParticipantDelegate(
			final HearingParticipantDao hearingParticipantDao,
			final InstanceFactory<HearingParticipant> 
				hearingParticipantInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingParticipantDao = hearingParticipantDao;
		this.hearingParticipantInstanceFactory =
				hearingParticipantInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Hearing Participant with the specified properties.
	 * 
	 * @param boardHearing - Board Hearing
	 * @param person - Person
	 * @param category - Hearing Participant Category
	 * @param boardApproved - Boolean Board Approved
	 * @param witness - Boolean Witness
	 * @param facilityApproved - Boolean Facility Approved
	 * @param intent - Hearing Participant Intent Category
	 * @param comments - String comments
	 * @return Newly created Hearing Participant
	 * @throws DuplicateEntityFoundException - When a Hearing Participant
	 * already exists with the given category for the specified Person.
	 */
	public HearingParticipant create(final BoardHearing boardHearing,
			final Person person, final HearingParticipantCategory category,
			final Boolean boardApproved, final Boolean witness,
			final Boolean facilityApproved,
			final HearingParticipantIntentCategory intent,
			final String comments)
				throws DuplicateEntityFoundException {
		if (this.hearingParticipantDao.find(boardHearing, person, category)
				!= null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		HearingParticipant hearingParticipant = 
				this.hearingParticipantInstanceFactory.createInstance();
		
		hearingParticipant.setBoardHearing(boardHearing);
		hearingParticipant.setPerson(person);
		hearingParticipant.setCategory(category);
		hearingParticipant.setBoardApproved(boardApproved);
		hearingParticipant.setWitness(witness);
		hearingParticipant.setFacilityApproved(facilityApproved);
		hearingParticipant.setIntent(intent);
		hearingParticipant.setComments(comments);
		hearingParticipant.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		hearingParticipant.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.hearingParticipantDao.makePersistent(hearingParticipant);
	}
	
	/**
	 * Updates a Hearing Participant with the specified properties.
	 * 
	 * @param hearingParticipant - Hearing Participant to update
	 * @param person - Person
	 * @param category - Hearing Participant Category
	 * @param boardApproved - Boolean Board Approved
	 * @param witness - Boolean Witness
	 * @param facilityApproved - Boolean Facility Approved
	 * @param intent - Hearing Participant Intent Category
	 * @param comments - String comments
	 * @return Updated Hearing Participant
	 * @throws DuplicateEntityFoundException - When a Hearing Participant
	 * already exists with the given category for the specified Person.
	 */
	public HearingParticipant update(
			final HearingParticipant hearingParticipant, final Person person,
			final HearingParticipantCategory category,
			final Boolean boardApproved, final Boolean witness,
			final Boolean facilityApproved,
			final HearingParticipantIntentCategory intent,
			final String comments)
				throws DuplicateEntityFoundException {
		if (this.hearingParticipantDao.findExcluding(
				hearingParticipant.getBoardHearing(), person, category,
				hearingParticipant) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		hearingParticipant.setPerson(person);
		hearingParticipant.setCategory(category);
		hearingParticipant.setBoardApproved(boardApproved);
		hearingParticipant.setWitness(witness);
		hearingParticipant.setFacilityApproved(facilityApproved);
		hearingParticipant.setIntent(intent);
		hearingParticipant.setComments(comments);
		hearingParticipant.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.hearingParticipantDao.makePersistent(hearingParticipant);
	}
	
	/**
	 * Removes the specified Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant to remove
	 */
	public void remove(final HearingParticipant hearingParticipant) {
		this.hearingParticipantDao.makeTransient(hearingParticipant);
	}
}
