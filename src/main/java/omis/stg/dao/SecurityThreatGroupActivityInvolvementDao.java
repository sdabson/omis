package omis.stg.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;

/**
 * Data access object for security threat group activity involvement.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 22, 2016)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupActivityInvolvementDao 
		extends GenericDao<SecurityThreatGroupActivityInvolvement> {

	/**
	 * Finds security threat group activity involvements.
	 * 
	 * @param SecurityThreatGroupActivity - activity
	 * 
	 * @return Finds security threat group activity involvements.
	 */
	List<SecurityThreatGroupActivityInvolvement> findInvolvements(
			SecurityThreatGroupActivity activity);
	
	/**
	 * Finds an offender to involve in a security threat group activity.
	 * 
	 * @param Offender - offender
	 * 
	 * @return Finds an offender to involve in a security threat group activity.
	 */
	List<Offender> findOffender(String queryString);
	
	/**
	 * Finds a security threat group involvement by offender, activity and
	 * narrative
	 * @param offender
	 * @param activity
	 * @param narrative
	 * @return security threat group involvement
	 */
	SecurityThreatGroupActivityInvolvement find(Offender offender, 
			SecurityThreatGroupActivity activity, String narrative);
	
	/**
	 * Finds a security threat group involvement by offender, activity and
	 * narrative
	 * @param offender
	 * @param activity
	 * @param narrative
	 * @param excludedInvolvement
	 * @return security threat group involvement
	 */
	SecurityThreatGroupActivityInvolvement findExcluding(Offender offender, 
			SecurityThreatGroupActivity activity, String narrative,
			SecurityThreatGroupActivityInvolvement excludedInvolvement);
	
}
