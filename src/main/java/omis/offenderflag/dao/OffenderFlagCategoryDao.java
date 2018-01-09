package omis.offenderflag.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Data access object for offender flag categories.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public interface OffenderFlagCategoryDao
		extends GenericDao<OffenderFlagCategory> {

	/**
	 * Returns categories for which offender flags are required.
	 * 
	 * @return categories for which offender flags are required
	 */
	List<OffenderFlagCategory> findRequired();
	
	/**
	 * Returns an offender flag category with specified name.
	 *
	 * @param name name
	 * @return offender flag category
	 */
	OffenderFlagCategory find(String name);
	
	/**
	 * Returns an offender flag category excluding the one in view.
	 *
	 * @param offenderFlagCategory offender flag category
	 * @param name name
	 * @return offender flag category
	 */
	OffenderFlagCategory findExcluding(OffenderFlagCategory 
			offenderFlagCategory, String name);
}