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
package omis.chronologicalnote.service.delegate;

import java.util.List;

import omis.chronologicalnote.dao.ChronologicalNoteCategoryDao;
import omis.chronologicalnote.domain.ChronologicalNoteCategory;

/**
 * Chronological note category delegate.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (February 1, 2018)
 * @since OMIS 3.0
 */
public class ChronologicalNoteCategoryDelegate {
	
	/* Data access objects. */
	private ChronologicalNoteCategoryDao chronologicalNoteCategoryDao;
	
	/**
	 * Instantiates a chronological note category delegate with the specified data access object.
	 * 
	 * @param chronologicalNoteCategoryDao chronological note category data access object
	 */
	public ChronologicalNoteCategoryDelegate(final ChronologicalNoteCategoryDao chronologicalNoteCategoryDao) {
		this.chronologicalNoteCategoryDao = chronologicalNoteCategoryDao;
	}
	
	/**
	 * Returns chronological note categories.
	 * 
	 * @return list of chronological note categories
	 */
	public List<ChronologicalNoteCategory> findAll() {
		return this.chronologicalNoteCategoryDao.findAll();
	}
}