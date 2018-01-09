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
package omis.supervision.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeReason;

/**
 * Data access object for placement term change reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 9, 2013)
 * @since OMIS 3.0
 */
public interface PlacementTermChangeReasonDao
		extends GenericDao<PlacementTermChangeReason> {

	/**
	 * Returns change reasons.
	 * 
	 * Note that setting the validity flags to {@code false} will return
	 * change reasons with {@code true} or {@code false} values for their
	 * respective properties (not exclusively {@code false}).
	 * 
	 * @param startReasons if {@code true} require that the change reasons be
	 * valid start reasons
	 * @param endReasons  if {@code true} require that the change reasons be
	 * valid end reasons
	 */
	List<PlacementTermChangeReason> find(
			boolean startReasons, boolean endReasons);
	
	/**
	 * Returns allowed placement term change reasons for correctional status.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @return allowed placement term change reasons for correctional status
	 */
	List<PlacementTermChangeReason> findAllowed(
			CorrectionalStatus fromCorrectionalStatus,
			CorrectionalStatus toCorrectionalStatus);

	/**
	 * Returns placement term change reason.
	 * 
	 * @param name name
	 * @return placement term change reason
	 */
	PlacementTermChangeReason find(String name);
	
	/**
	 * Returns allowed placement term start change reasons for correctional 
	 * status.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @return allowed placement term change reasons for correctional status
	 */
	List<PlacementTermChangeReason> findAllowedStartChangeReasons(
			CorrectionalStatus fromCorrectionalStatus,
			CorrectionalStatus toCorrectionalStatus);

	/**
	 * Returns allowed placement term end change reasons for correctional 
	 * status.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @return allowed placement term change reasons for correctional status
	 */
	List<PlacementTermChangeReason> findAllowedEndChangeReasons(
			CorrectionalStatus fromCorrectionalStatus);
}