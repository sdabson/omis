/**
 * 
 */
package omis.specialneed.dao;

import omis.dao.GenericDao;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Data access object for special need source.
 * 
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.2 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedSourceDao 
	extends GenericDao<SpecialNeedSource> {
	
	/**
	 * Returns special need source matching the specified name.
	 * 
	 * @param name name
	 * @return special need source
	 */
	SpecialNeedSource find(String name);
	
	/**
	 * Returns special need source matching the specified name, excluding the 
	 * specified source.
	 * 
	 * @param name name
	 * @param excludedSource excluded special need source
	 * @return special need source
	 */
	SpecialNeedSource findExcluding(String name, 
			SpecialNeedSource excludedSource);
}