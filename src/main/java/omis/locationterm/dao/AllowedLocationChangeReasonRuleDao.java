package omis.locationterm.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.locationterm.domain.AllowedLocationChangeReasonRule;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Data access object for allowed location change reason rule.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface AllowedLocationChangeReasonRuleDao
		extends GenericDao<AllowedLocationChangeReasonRule> {

	/**
	 * Returns location reasons for change action.
	 * 
	 * @param changeAction change action
	 * @return location reasons for change action
	 */
	List<LocationReason> findLocationReasonsForChangeAction(
			LocationTermChangeAction changeAction);
}