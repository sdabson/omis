package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.MaritalStatus;

/**
 * Service for marital statuses.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public interface MaritalStatusService {

	/**
	 * Returns marital statuses.
	 * 
	 * @return marital statuses
	 */
	List<MaritalStatus> findAll();
}