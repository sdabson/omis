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
package omis.program.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.program.domain.Program;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Data access object for programs.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 8, 2015)
 * @since OMIS 3.0
 */
public interface ProgramDao
		extends GenericDao<Program> {

	/**
	 * Returns programs offered by supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @return programs offered by supervisory organization
	 */
	List<Program> findOfferedBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Returns programs offered by location.
	 * 
	 * @param location location
	 * @return program offered by location
	 */
	List<Program> findOfferedByLocation(Location location);
	
	/**
	 * Returns program with the specified name.
	 * 
	 * @param name name
	 * @return program with the specified name
	 */
	Program find(String name);
	
	/**
	 * Returns program with the specified name excluding the specified program.
	 * 
	 * @param name name
	 * @param excludedProgram excluded program
	 * @return program with the specified name excluding the specified program
	 */
	Program findExcluding(String name, Program excludedProgram);
}