package omis.substanceuse.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;
import omis.substanceuse.domain.SubstanceUse;

/**
 * Substance use data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 17, 2016)
 * since OMIS 3.0
 */
public interface SubstanceUseDao extends GenericDao<SubstanceUse> {
	
	/**
	 * Returns all substance uses for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of substance uses
	 */
	List<SubstanceUse> findByOffender(Offender offender);
	
	/**
	 * Returns the substance use with the specified offender and substance.
	 * 
	 * @param offender offender
	 * @param substance substance
	 * @return substance use
	 */
	SubstanceUse find(Offender offender, Substance substance);
	
	/**
	 * Returns the substance with the specified offender and substance,
	 * except the specified substance use.
	 * 
	 * @param use substance use
	 * @param offender offender
	 * @param substance substance
	 * @return substance use
	 */
	SubstanceUse findExcluding(SubstanceUse use, Offender offender,
			Substance substance);
}