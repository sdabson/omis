package omis.program.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.program.dao.ProgramDao;
import omis.program.domain.Program;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Delegate for programs.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 14, 2015)
 * @since OMIS 3.0
 */
public class ProgramDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<Program> programInstanceFactory;
	
	/* Data access objects. */
	
	private final ProgramDao programDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for programs.
	 * 
	 * @param programDao data access object for programs
	 */
	public ProgramDelegate(final ProgramDao programDao,
			final InstanceFactory<Program> programInstanceFactory) {
		this.programDao = programDao;
		this.programInstanceFactory = programInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Returns programs.
	 * 
	 * @return programs
	 */
	public List<Program> findAll() {
		return this.programDao.findAll();
	}
	
	/**
	 * Returns programs offered by supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @return programs offered by supervisory organization
	 */
	public List<Program> findOfferedBySupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		return this.programDao.findOfferedBySupervisoryOrganization(
				supervisoryOrganization);
	}
	
	/**
	 * Returns programs offered by location.
	 * 
	 * @param location location
	 * @return programs offered by location
	 */
	public List<Program> findOfferedByLocation(final Location location) {
		return this.programDao.findOfferedByLocation(location);
	}
	
	/**
	 * Creates a new program with the specified name.
	 * 
	 * @param name name
	 * @return new program with the specified name
	 * @throws DuplicateEntityFoundException thrown when program already exists
	 */
	public Program create(final String name) 
			throws DuplicateEntityFoundException {
		if (this.programDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Program already exists.");
		}
		Program program = this.programInstanceFactory.createInstance();
		program.setName(name);
		program.setActive(true);
		return this.programDao.makePersistent(program);
	}
}