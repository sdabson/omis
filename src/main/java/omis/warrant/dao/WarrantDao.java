package omis.warrant.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.warrant.domain.Warrant;

/**
 * WarrantDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantDao extends GenericDao<Warrant> {
	
	/**
	 * Finds and returns a Warrant found by specified Offender and date
	 * @param offender - Offender
	 * @param date - Date
	 * @return Warrant found by specified Offender
	 */
	public Warrant find(Offender offender, Date date);
	
	/**
	 * Finds and returns a Warrant found by specified Offender and date excluding
	 * specified Warrant
	 * @param offender - Offender
	 * @param date - Date
	 * @param warrantExcluded - Warrant to exclude from search
	 * @return Warrant found by specified Offender excluding specified warrant
	 */
	public Warrant findExcluding(Offender offender, Date date,
			Warrant warrantExcluded);
	
	/**
	 * Returns a List of Warrants for the specified Offender
	 * @param offender - Offender
	 * @return List of Warrants
	 */
	public List<Warrant> findByOffender(Offender offender);
}
