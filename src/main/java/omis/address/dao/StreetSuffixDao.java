package omis.address.dao;

import omis.address.domain.StreetSuffix;
import omis.dao.GenericDao;

/**
 * Data access object for street suffixes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public interface StreetSuffixDao
		extends GenericDao<StreetSuffix> {

	/**
	 * Returns street suffix.
	 * 
	 * @param name name of street suffix
	 * @return street suffix
	 */
	StreetSuffix find(String name);
}