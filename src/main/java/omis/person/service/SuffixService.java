package omis.person.service;

import java.util.List;

import omis.person.domain.Suffix;

/**
 * Service for suffixes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 13, 2013)
 * @since OMIS 3.0
 */
public interface SuffixService {

	/**
	 * Returns all suffixes.
	 * 
	 * @return all suffixes
	 */
	List<Suffix> findAll();
}