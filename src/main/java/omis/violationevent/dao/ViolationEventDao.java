package omis.violationevent.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.supervision.domain.SupervisoryOrganization;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventCategory;

/**
 * ViolationEventDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 30, 2017)
 *@since OMIS 3.0
 *
 */
public interface ViolationEventDao extends GenericDao<ViolationEvent> {
	
	
	/**
	 * Finds and returns a ViolationEvent with specified properties
	 * @param offender - offender
	 * @param jurisdiction - SupervisoryOrganization
	 * @param date - Date
	 * @param details - String
	 * @param category - ViolationEventCategory
	 * @return ViolationEvent with specified properties
	 */
	ViolationEvent find(Offender offender, SupervisoryOrganization jurisdiction,
			Date date, String details, ViolationEventCategory category);
	
	/**
	 * Finds and returns a ViolationEvent with specified properties excluding
	 * specified ViolationEvent
	 * @param excludedViolationEvent - ViolationEvent to exclude
	 * @param offender - Offender
	 * @param jurisdiction - SupervisoryOrganization
	 * @param date - Date
	 * @param details - String
	 * @param category - ViolationEventCategory
	 * @return ViolationEvent with specified properties excluding
	 * specified ViolationEvent
	 */
	ViolationEvent findExcluding(ViolationEvent excludedViolationEvent,
			Offender offender, SupervisoryOrganization jurisdiction,
			Date date, String details, ViolationEventCategory category);
	
	/**
	 * Finds and returns a list of ViolationEvents by specified offender
	 * @param offender - Offender 
	 * @return List of ViolationEvents by specified offender
	 */
	List<ViolationEvent> findByOffender(Offender offender);
	
	/**
	 * Finds and returns a list of ViolationEvents with no Infraction/resolution
	 * association by specified offender
	 * @param offender - Offender 
	 * @return List of ViolationEvents with no Infraction/resolution
	 * association by specified offender
	 */
	List<ViolationEvent> findUnresolvedByOffender(Offender offender);
}
