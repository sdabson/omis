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
package omis.paroleeligibility.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.domain.ParoleEligibilityNote;

/**
 * Parole eligibility note data access object.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface ParoleEligibilityNoteDao 
	extends GenericDao<ParoleEligibilityNote> {

	/**
	 * Finds all parole eligibility notes by parole eligibility.
	 * 
	 * @param paroleEligibility parole eligibility
	 * @return parole eligibility notes by parole eligibility
	 */
	List<ParoleEligibilityNote> findParoleEligibilityNotesByParoleEligibility(
			ParoleEligibility paroleEligibility);

	/**
	 * Finds a parole eligibility note.
	 * 
	 * @param paroleEligibility parole eligibility
	 * @param description description of the parole eligibility note
	 * @param date date of the parole eligibility note
	 * @return parole eligibility note
	 */
	ParoleEligibilityNote findNote(ParoleEligibility paroleEligibility, 
			String description, Date date);

	/**
	 * Finds a parole eligibility note, not including the specified note.
	 * 
	 * @param paroleEligibilityNote parole eligibility note
	 * @param paroleEligibility parole eligibility
	 * @param description description of the parole eligibility note
	 * @param date date of the parole eligibility note
	 * @return parole eligibility note, not including the specified note
	 */
	ParoleEligibilityNote findNoteExcluding(
			ParoleEligibilityNote paroleEligibilityNote, 
			ParoleEligibility paroleEligibility, String description, Date date);
	
}
