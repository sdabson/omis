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