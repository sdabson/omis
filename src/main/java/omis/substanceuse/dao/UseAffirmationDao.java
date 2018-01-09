package omis.substanceuse.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseAffirmation;
import omis.substanceuse.domain.UseAffirmationSource;

/**
 * Use affirmation data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 17, 2016)
 * @since OMIS 3.0
 */
public interface UseAffirmationDao extends GenericDao<UseAffirmation> {
	
	/**
	 * Returns use affirmations for the specified substance use.
	 * 
	 * @param use substance use
	 * @return list of use affirmations
	 */
	List<UseAffirmation> findBySubstanceUse(SubstanceUse use);
	
	/**
	 * Returns the use affirmation with the specified date, substance use, and 
	 * use affirmation source.
	 * 
	 * @param date date
	 * @param use substance use
	 * @param source use affirmation source
	 * @return use affirmation
	 */
	UseAffirmation find(Date date, SubstanceUse use,
			UseAffirmationSource source);
	
	/**
	 * Returns the use affirmation with the specified date, substance use, and
	 * use affirmation source excluding the specified use affirmation.
	 * 
	 * @param affirmation use affirmation
	 * @param date date
	 * @param use substance use
	 * @param source use affirmation 
	 * @return use affirmation
	 */
	UseAffirmation findExcluding(UseAffirmation affirmation, Date date,
			SubstanceUse use, UseAffirmationSource source);
}