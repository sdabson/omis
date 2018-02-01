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
package omis.docket.dao;

import java.util.List;

import omis.court.domain.Court;
import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

/**
 * Data access object for dockets.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface DocketDao
		extends GenericDao<Docket> {

	/**
	 * Returns docket or {@code null} if not found.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @return docket or {@code null} if not found
	 */
	Docket find(Person person, Court court, String value);
	
	/**
	 * Returns docket or {@code null} if not found or excluded.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @param excludedDockets dockets to exclude
	 * @return docket or {@code null} if not found or excluded
	 */
	Docket findExcluding(Person person, Court court, String value,
			Docket... excludedDockets);
	
	/**
	 * Returns a list of dockets for the specified Person
	 * @param person - person
	 * @return List of dockets for the specified person
	 */
	List<Docket> findByPerson(Person person);
}