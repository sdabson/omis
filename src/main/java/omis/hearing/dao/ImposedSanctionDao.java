package omis.hearing.dao;


import omis.dao.GenericDao;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;

/**
 * ImposedSanctionDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface ImposedSanctionDao extends GenericDao<ImposedSanction> {
	
	/**
	 * Returns an ImposedSanction found with specified properties
	 * @param infraction - Infraction
	 * @param description - String
	 * @return ImposedSanction found with specified properties
	 */
	public ImposedSanction find(Infraction infraction, String description);
	
	/**
	 * Returns an ImposedSanction found with specified properties excluding
	 * specified ImposedSanction
	 * @param infraction - Infraction
	 * @param description - String
	 * @param imposedSanctionExcluded - ImposedSanction to exclude from search
	 * @return ImposedSanction found with specified properties excluding
	 * specified ImposedSanction
	 */
	public ImposedSanction findExcluding(Infraction infraction, String description,
			ImposedSanction imposedSanctionExcluded);
	
	/**
	 * Returns an ImposedSanction found by specified Infraction
	 * @param infraction - Infraction
	 * @return ImposedSanction found by specified Infraction
	 */
	public ImposedSanction findByInfraction(Infraction infraction);
	
}
