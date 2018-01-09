package omis.substanceuse.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseTerm;
import omis.substanceuse.domain.UseTermSource;

/**
 * Use term data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 17, 2016)
 * @since OMIS 3.0
 */
public interface UseTermDao extends GenericDao<UseTerm> {

	/**
	 * Returns use terms for the specified substance use.
	 * 
	 * @param use substance use
	 * @return list of use terms
	 */
	List<UseTerm> findUseTermsBySubstanceUse(SubstanceUse use);
	
	/**
	 * Returns the use term with the specified start date, substance use,
	 * and use term source.
	 * 
	 * @param startDate start date
	 * @param use substance use
	 * @param source use term source
	 * @return use term
	 */
	UseTerm find(Date startDate, SubstanceUse use, UseTermSource source);
	
	/**
	 * Returns the use term with the specified start date, substance use,
	 * and use term source, excluding the specified use term.
	 * 
	 * @param term excluded use term
	 * @param startDate start date
	 * @param use substance use
	 * @param source use term source
	 * @return use term
	 */
	UseTerm findExcluding(UseTerm term, Date startDate, SubstanceUse use,
			UseTermSource source);

	/**
	 * Returns any use terms for the specified substance use within the
	 * specified date range.
	 * 
	 * @param use substance use
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of use terms
	 */
	List<UseTerm> findByUseInDateRange(SubstanceUse use, Date startDate,
			Date endDate);
}