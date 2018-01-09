package omis.religion.dao;

import omis.dao.GenericDao;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligionGroup;

/**
 * Data access object for religion.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.0 (Jul 26, 2017)
 * @since OMIS 3.0
 */
public interface ReligionDao
		extends GenericDao<Religion> {

	/**
	 * Returns the religion with the specified name and group.
	 * 
	 * @param name name
	 * @param group religion group
	 * @param excludedReligion excluded religion
	 * @return religion
	 */
	public Religion find(String name, ReligionGroup group);

	/**
	 * Returns the religion with the specified name and group excluding the 
	 * specified religion.
	 * 
	 * @param name name
	 * @param group religion group
	 * @param excludedReligion excluded religion
	 * @return religion
	 */
	public Religion findExcluding(String name, ReligionGroup group, Religion excludedReligion);
}