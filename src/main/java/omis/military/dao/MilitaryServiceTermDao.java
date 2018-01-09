package omis.military.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryServiceTerm;
import omis.offender.domain.Offender;

/**
 * Military service term data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public interface MilitaryServiceTermDao 
extends GenericDao<MilitaryServiceTerm>{

	/**
	 * Returns the military service term with the specified properties.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param branch military branch
	 * @return matching military service term
	 */
	MilitaryServiceTerm find(Offender offender, Date startDate, 
			MilitaryBranch branch);
	
	/**
	 * Returns the military service term with the specified properties, except
	 * for the specified service term.
	 * 
	 * @param serviceTerm military service term
	 * @param offender offender
	 * @param startDate start date
	 * @param militaryBranch military branch
	 * @return matching military service term
	 */
	MilitaryServiceTerm findExcluding(MilitaryServiceTerm serviceTerm, 
			Offender offender, Date startDate, MilitaryBranch militaryBranch);

	/**
	 * Returns a list of military service terms that occur between the specified
	 * start date and end date.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of military service terms.
	 */
	List<MilitaryServiceTerm> findWithinDateRange(Offender offender, 
			Date startDate, Date endDate);

	/**
	 * Returns a list of military service terms that occur between the specified
	 * start and end date, except for the specified service term.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param serviceTerm military service term
	 * @return list of military service terms
	 */
	List<MilitaryServiceTerm> findWithinDateRangeExcluding(Offender offender,
			Date startDate, Date endDate, MilitaryServiceTerm serviceTerm);

	/**
	 * Returns a list of military service terms for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of military service terms
	 */
	List<MilitaryServiceTerm> findByOffender(Offender offender);
}