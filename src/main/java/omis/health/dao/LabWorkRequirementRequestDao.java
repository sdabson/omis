package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.LabWorkRequirementRequest;

/**
 * Data access object for lab work requirement requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 3, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkRequirementRequestDao
		extends GenericDao<LabWorkRequirementRequest> {

	/**
	 * Returns lab work requirement requests by health request.
	 * 
	 * @param healthRequest health request
	 * @return lab work requirement requests by health request
	 */
	List<LabWorkRequirementRequest> findByHealthRequest(
			HealthRequest healthRequest);
}