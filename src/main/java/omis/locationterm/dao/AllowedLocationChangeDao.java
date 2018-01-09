package omis.locationterm.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.locationterm.domain.AllowedLocationChange;
import omis.locationterm.domain.LocationTermChangeAction;
import omis.region.domain.State;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Data access object for allowed location change.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
public interface AllowedLocationChangeDao
		extends GenericDao<AllowedLocationChange> {

	/**
	 * Returns locations allowed for location change action for an offender
	 * on a correctional status.
	 * 
	 * @param action action
	 * @param correctionalStatus correctional status
	 * @return locations allowed for action for an offender on
	 * correctional status
	 */
	List<Location> findLocationsForActionForCorrectionalStatus(
			LocationTermChangeAction action,
			CorrectionalStatus correctionalStatus);

	/**
	 * Returns locations allowed for location change action for an offender
	 * on a correctional status in a State.
	 * 
	 * @param action action
	 * @param correctionalStatus correctional status
	 * @param state State
	 * @return locations allowed for action for an offender on correctional
	 * status in State
	 */
	List<Location> findLocationsForActionForCorrectionalStatusInState(
			final LocationTermChangeAction action,
			final CorrectionalStatus correctionalStatus,
			final State state);
	
	/**
	 * Returns locations allowed for any change.
	 *  
	 * <p>Effectively, this methods returns any location at which an offender
	 * can be housed.
	 *  
	 * @return locations allowed for any change
	 */
	List<Location> findLocationsAllowedForAnyChange();
	
	/**
	 * Returns locations allowed for any change in State.
	 *  
	 * <p>Effectively, this methods returns any location at which an offender
	 * can be housed in State.
	 * 
	 * @param state State
	 * @return locations allowed for any change in State
	 */
	List<Location> findLocationsAllowedForAnyChangeInState(State state);
}