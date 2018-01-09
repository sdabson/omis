package omis.health.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Unit;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderUnitAssignment;

/**
 * Provider Unit Assignment Data Access Object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 9, 2014)
 * @since OMIS 3.0
 */
public interface ProviderUnitAssignmentDao 
	extends GenericDao<ProviderUnitAssignment> {

	/**
	 * Returns the provider unit assignment with the specified provider
	 * assignment and unit.
	 * 
	 * @param providerAssignment provider assignment
	 * @param unit unit
	 * @return provider assignment; {@code null} if no provider unit assignment
	 * found
	 */
	ProviderUnitAssignment find(ProviderAssignment providerAssignment, 
			Unit unit);

	/**
	 * Returns the provider unit assignments for the specified unit on the
	 * specified date.
	 * 
	 * @param unit unit
	 * @param date date
	 * @return list of provider unit assignments; {@code null} if no
	 * provider unit assignments found
	 */
	List<ProviderUnitAssignment> findByUnit(Unit unit, Date date);
}