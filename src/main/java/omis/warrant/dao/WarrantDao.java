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
package omis.warrant.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.warrant.domain.Warrant;

/**
 * Warrant data access object.
 * 
 *@author Annie Jacques
 *@author Joel Norris 
 *@version 0.1.1 (January 24, 2018)
 *@since OMIS 3.0
 *
 */
public interface WarrantDao extends GenericDao<Warrant> {
	
	/**
	 * Finds and returns warrant with the specified offender and date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return warrant
	 */
	public Warrant find(Offender offender, Date date);
	
	/**
	 * Returns warrant with the specified offender and date, excluding the specified warrant.
	 * 
	 * @param offender offender
	 * @param date date
	 * @param warrantExcluded excluded warrant
	 * @return warrant
	 */
	public Warrant findExcluding(Offender offender, Date date,
			Warrant warrantExcluded);
	
	/**
	 * Returns warrants for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of warrants
	 */
	public List<Warrant> findByOffender(Offender offender);
}
