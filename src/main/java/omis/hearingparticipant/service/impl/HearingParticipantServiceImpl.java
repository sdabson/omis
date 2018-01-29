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
package omis.hearingparticipant.service.impl;

import java.util.Date;
import java.util.List;
import omis.boardhearing.domain.BoardHearing;
import omis.exception.DuplicateEntityFoundException;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;
import omis.hearingparticipant.domain.HearingParticipantNote;
import omis.hearingparticipant.service.HearingParticipantService;
import omis.hearingparticipant.service.delegate.HearingParticipantDelegate;
import omis.hearingparticipant.service.delegate.HearingParticipantIntentCategoryDelegate;
import omis.hearingparticipant.service.delegate.HearingParticipantNoteDelegate;
import omis.person.domain.Person;

/**
 * Hearing Participant Service Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 17, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantServiceImpl
		implements HearingParticipantService {
	
	private final HearingParticipantDelegate hearingParticipantDelegate;
	
	private final HearingParticipantNoteDelegate hearingParticipantNoteDelegate;
	
	private final HearingParticipantIntentCategoryDelegate
		hearingParticipantIntentCategoryDelegate;
	
	/**
	 * @param hearingParticipantDelegate - Hearing Participant Delegate
	 * @param hearingParticipantNoteDelegate - Hearing Participant Note Delegate
	 * @param hearingParticipantIntentCategoryDelegate - Hearing Participant
	 * Intent Category Delegate
	 */
	public HearingParticipantServiceImpl(
			final HearingParticipantDelegate hearingParticipantDelegate,
			final HearingParticipantNoteDelegate hearingParticipantNoteDelegate,
			final HearingParticipantIntentCategoryDelegate
				hearingParticipantIntentCategoryDelegate) {
		this.hearingParticipantDelegate = hearingParticipantDelegate;
		this.hearingParticipantNoteDelegate = hearingParticipantNoteDelegate;
		this.hearingParticipantIntentCategoryDelegate =
				hearingParticipantIntentCategoryDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipant createHearingParticipant(
			final BoardHearing boardHearing, final Person person,
			final HearingParticipantCategory category,
			final Boolean boardApproved, final Boolean witness,
			final Boolean facilityApproved,
			final HearingParticipantIntentCategory intent,
			final String comments)
					throws DuplicateEntityFoundException {
		return this.hearingParticipantDelegate.create(boardHearing, person,
				category, boardApproved, witness, facilityApproved, intent,
				comments);
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipant updateHearingParticipant(
			final HearingParticipant hearingParticipant, final Person person,
			final HearingParticipantCategory category,
			final Boolean boardApproved, final Boolean witness,
			final Boolean facilityApproved,
			final HearingParticipantIntentCategory intent,
			final String comments)
					throws DuplicateEntityFoundException {
		return this.hearingParticipantDelegate.update(hearingParticipant,
				person, category, boardApproved, witness, facilityApproved,
				intent, comments);
	}

	/**{@inheritDoc} */
	@Override
	public void removeHearingParticipant(
			final HearingParticipant hearingParticipant) {
		this.hearingParticipantDelegate.remove(hearingParticipant);
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipantNote createHearingParticipantNote(
			final HearingParticipant hearingParticipant,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.hearingParticipantNoteDelegate.create(hearingParticipant,
				description, date);
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipantNote updateHearingParticipantNote(
			final HearingParticipantNote hearingParticipantNote,
			final HearingParticipant hearingParticipant,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.hearingParticipantNoteDelegate.update(
				hearingParticipantNote, hearingParticipant, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeHearingParticipantNote(
			final HearingParticipantNote hearingParticipantNote) {
		this.hearingParticipantNoteDelegate.remove(hearingParticipantNote);
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingParticipantNote>
			findHearingParticipantNotesByParticipant(
					final HearingParticipant hearingParticipant) {
		return this.hearingParticipantNoteDelegate.findByHearingParticipant(
				hearingParticipant);
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingParticipantIntentCategory>
			findHearingParticipantIntentCategories() {
		return this.hearingParticipantIntentCategoryDelegate.findAll();
	}
}
