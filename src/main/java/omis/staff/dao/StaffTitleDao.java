package omis.staff.dao;

import omis.dao.GenericDao;
import omis.staff.domain.StaffTitle;

/**
 * Data access object for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 21, 2013)
 * @since OMIS 3.0
 */
public interface StaffTitleDao
		extends GenericDao<StaffTitle> {

	/**
	 * Returns the highest sort order.
	 * 
	 * @return highest sort order
	 */
	short findHighestSortOrder();
	
	/**
	 * Returns staff title with name.
	 * 
	 * @param name name
	 * @return staff title with name or {@code null} if no staff title exists
	 * with the name.
	 */
	StaffTitle find(String name);
	
	/**
	 * Returns the staff title with the name excluding staff titles specified.
	 * 
	 * @param name name
	 * @param excludedStaffTitles staff titles to exclude
	 * @return staff title with name or {@code null} if no staff title exists
	 * with the name
	 */
	StaffTitle findExcluding(String name, StaffTitle... excludedStaffTitles);
}