package omis.locationterm.service.delegate;

import java.util.List;

import omis.locationterm.dao.AllowedLocationChangeReasonRuleDao;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Delegate for allowed location change reason rules.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class AllowedLocationChangeReasonRuleDelegate {

	private final AllowedLocationChangeReasonRuleDao
	allowedLocationChangeReasonRuleDao;
	
	/**
	 * Instantiates allowed location change reason rule delegate.
	 * 
	 * @param allowedLocationChangeReasonRuleDao data access for allowed
	 * location change reason rule
	 */
	public AllowedLocationChangeReasonRuleDelegate(
			final AllowedLocationChangeReasonRuleDao
				allowedLocationChangeReasonRuleDao) {
		this.allowedLocationChangeReasonRuleDao
			= allowedLocationChangeReasonRuleDao;
	}
	
	/**
	 * Returns location reasons by change action.
	 * 
	 * @param changeAction location reasons by change action
	 * @return location reasons by changge action
	 */
	public List<LocationReason> findLocationReasonsForChangeAction(
			final LocationTermChangeAction changeAction) {
		return this.allowedLocationChangeReasonRuleDao
				.findLocationReasonsForChangeAction(changeAction);
	}
}