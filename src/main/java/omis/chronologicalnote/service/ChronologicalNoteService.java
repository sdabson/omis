/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.chronologicalnote.service;

import java.util.Date;
import java.util.List;

import omis.chronologicalnote.domain.ChronologicalNote;
import omis.chronologicalnote.domain.ChronologicalNoteCategory;
import omis.chronologicalnote.exception.ChronologicalNoteExistsException;
import omis.offender.domain.Offender;

/**
 * Chronological note service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (January 29, 2018)
 * @since OMIS 3.0
 */
public interface ChronologicalNoteService {

	/**
	 * Creates a chronological note with the specified date, offender, and narrative.
	 * 
	 * @param date date
	 * @param offender offender
	 * @param narrative narrative
	 * @return newly created chronological note
	 * @throws ChronologicalNoteExistsException Thrown if a duplicate chronological note exists.
	 */
	ChronologicalNote create(Date date, Offender offender, String narrative) throws ChronologicalNoteExistsException;
	
	/**
	 * Updates the specified note with the specified date and narrative.
	 * 
	 * @param note chronological note
	 * @param date date
	 * @param narrative narrative
	 * @return updated chronological note
	 * @throws ChronologicalNoteExistsException Thrown if a duplicate chronological note exists.
	 */
	ChronologicalNote update(ChronologicalNote note, Date date, String narrative) throws ChronologicalNoteExistsException;
	
	/**
	 * Removes the specified chronological note.
	 * 
	 * @param note chronological note 
	 */
	void remove(ChronologicalNote note);
	
	/**
	 * Associates the specified chronological note and chronological note category.
	 * 
	 * <p>
	 * Throws an {@code IllegalArgumentException} if an association already exists between the
	 * specified chronological note and chronological note category.
	 * 
	 * @param note chronological note
	 * @param category chronological note category
	 */
	void associateCategory(ChronologicalNote note, ChronologicalNoteCategory category);
	
	/**
	 * Dissociates the specified chronological note and chronological note category.
	 * 
	 * <p>
	 * Throws an {@code IllegalArgumentException} if no association exists between the specified chronological note and
	 * chronological note category. 
	 * 
	 * @param note chronological note
	 * @param category chronological note category
	 */
	void dissociateCategory(ChronologicalNote note, ChronologicalNoteCategory category);
	
	/**
	 * Returns chronological note categories.
	 * 
	 * @return list of chronological note categories
	 */
	List<ChronologicalNoteCategory> findCategories();
	
	/**
	 * Returns all chronological note categories associated with the specified chronological note.
	 * 
	 * @param note chronological note
	 * @return list of chronological note categories
	 */
	List<ChronologicalNoteCategory> findAssociatedCategories(ChronologicalNote note);
}