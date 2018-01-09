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
import omis.region.domain.State;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Data access object for organizations that provide correctional supervision.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 16, 2013)
 * @since OMIS 3.0
 */
public interface SupervisoryOrganizationDao
		extends GenericDao<SupervisoryOrganization> {

	/**
	 * Returns supervisory organizations allowed for correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @return supervisory organizations allowed for correctional status
	 */
	List<SupervisoryOrganization> findForCorrectionalStatus(
			CorrectionalStatus correctionalStatus);

	/**
	 * Returns supervisory organization with name.
	 * 
	 * @param name name
	 * @return supervisory organization
	 */
	SupervisoryOrganization find(String name);

	/**
	 * Returns supervisory organizations by State.
	 * 
	 * @param state State
	 * @return supervisory organizations by State
	 */
	List<SupervisoryOrganization> findByState(State state);
	
	/**
	 * Returns supervisory organizations by correctional status for State.
	 * 
	 * @param correctionalStatus correctional status
	 * @param state State
	 * @return supervisory organizations by correctional status for State
	 */
	List<SupervisoryOrganization> findByCorrectionalStatusForState(
			CorrectionalStatus correctionalStatus, State state);
	
	/**
	 * Returns supervisory organizations allowed for placement.
	 * 
	 * @return supervisory organizations allowed for placement
	 */
	List<SupervisoryOrganization> findAllowedForPlacement();
	
	/**
	 * Returns supervisory organizations allowed for placement in State.
	 * 
	 * @param state State
	 * @return supervisory organizations allowed for placement in State
	 */
	List<SupervisoryOrganization> findAllowedForPlacementInState(State state);
}