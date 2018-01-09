package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.Build;

/**
 * Service for builds.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public interface BuildService {

	/**
	 * Returns all builds.
	 * 
	 * @return all builds
	 */
	List<Build> findAll();
}