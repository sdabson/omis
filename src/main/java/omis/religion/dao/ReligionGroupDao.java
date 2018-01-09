package omis.religion.dao;

import omis.dao.GenericDao;
import omis.religion.domain.ReligionGroup;

public interface ReligionGroupDao 
	extends GenericDao<ReligionGroup> {
	
	/**
	 * Returns the religion group with the specified name.
	 * 
	 * @param name name
	 * @return religion group
	 */
	public ReligionGroup find(String name);
	
	/**
	 * Returns the religion group with the specified name, excluding the 
	 * specified religion group.
	 * 
	 * @param name name
	 * @param excludedReligionGroup excluded religion group
	 * @return religion group
	 */
	public ReligionGroup findExcluding(String name, 
			ReligionGroup excludedReligionGroup);

}
