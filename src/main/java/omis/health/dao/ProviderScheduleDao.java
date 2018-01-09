package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;

/**
 * Provider Schedule Data Access Object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 */
public interface ProviderScheduleDao 
	extends GenericDao<ProviderSchedule> {

	/**
	 * Returns the provider schedule with the specified provider assignment.
	 * 
	 * @param providerAssignment provider assignment
	 * @return provider schedule; {@code null} if no schedule found
	 */
	ProviderSchedule findByAssignment(ProviderAssignment providerAssignment);
}