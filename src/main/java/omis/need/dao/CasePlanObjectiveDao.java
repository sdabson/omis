package omis.need.dao;

import omis.dao.GenericDao;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.NeedDomain;
import omis.offender.domain.Offender;

/**
 * Case plan objective data access object.
 * 
 * @author Kelly Churchill
 * @author Joel Norris
 * @version 0.1.0 (July 07, 2015)
 * @since OMIS 3.0
 *
 */
public interface CasePlanObjectiveDao extends GenericDao<CasePlanObjective>{

	/**
	 * Returns the case plan objective with the specified values.
	 * 
	 * @param offender offender
	 * @param name name
	 * @param domain need domain
	 * @return case plan objective
	 */
	CasePlanObjective find(Offender offender, String name,
			NeedDomain domain);

	/**
	 * Returns the case plan objective with the specified values, excluding the
	 * specified case plan objective.
	 * 
	 * @param objective case plan objective
	 * @param offender offender
	 * @param name name
	 * @param domain need domain
	 * @return case plan objective
	 */
	CasePlanObjective findExcluding(CasePlanObjective objective,
			Offender offender, String name, NeedDomain domain);
}