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

import omis.dao.GenericDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;

/**
 * Warrant cancellation data access object.
 * 
 *@author Annie Jacques 
 *@author Joel Norris
 *@version 0.1.1 (January 24, 2018)
 *@since OMIS 3.0
 *
 */
public interface WarrantCancellationDao extends GenericDao<WarrantCancellation> {
	
	/**
	 * Returns warrant cancellation for the specified warrant.
	 * 
	 * @param warrant warrant
	 * @return warrant cancellation
	 */
	public WarrantCancellation find(Warrant warrant);

	/**
	 * Returns warrant cancellation for the specified warrant excluding the specified warrant cancellation.
	 * 
	 * @param warrant warrant
	 * @param warrantCancellationExcluding warrant cancellation excluding
	 * @return warrant cancellation
	 */
	public WarrantCancellation findExcluding(Warrant warrant,
			WarrantCancellation warrantCancellationExcluding);
	
}
