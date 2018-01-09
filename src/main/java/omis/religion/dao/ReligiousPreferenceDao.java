package omis.religion.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousPreference;

/**
 * Data access object for religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousPreferenceDao
		extends GenericDao<ReligiousPreference> {

	/**
	 * Returns religious preferences by offender.
	 * 
	 * @param offender offender
	 * @return religious preferences by offender
	 */
	List<ReligiousPreference> findByOffender(Offender offender);

	/**
	 * Returns religious preferences for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return religious preferences for offender on date
	 */
	ReligiousPreference findByOffenderOnDate(
			Offender offender, Date date);
	
	/**
	 * Returns religious preferences for offender on date excluding
	 * preferences.
	 * 
	 * @param offender offender
	 * @param date date
	 * @param excludedPreferences preferences to exclude
	 * @return religious preferences for offender on date
	 */
	ReligiousPreference findByOffenderOnDateExcluding(
			Offender offender, Date date,
			ReligiousPreference... excludedPreferences);
	
	/**
	 * Returns count of religious preferences for offender on date excluding
	 * preferences.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedPreferences preferences to exclude
	 * @return religious preferences for offender on date
	 */
	long countByOffenderOnDateExcluding(
			Offender offender, Date startDate, Date endDate,
			ReligiousPreference... excludedPreferences);
	
	/**
	 * Returns count of religious preferences for offender on date.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return religious preferences for offender on date
	 */
	long countByOffenderOnDate(Offender offender, Date startDate, Date endDate);
	
	/**
	 * Returns whether the religious preference exists.
	 * 
	 * @param offender offender
	 * @param religion religion
	 * @param startDate start date
	 * @param endDate end date
	 * @return whether religious preference exists
	 */
	boolean exists(Offender offender, Religion religion, Date startDate,
			Date endDate);
	
	/**
	 * Returns whether the religious preference exists excluding the
	 * preferences.
	 * 
	 * @param offender offender
	 * @param religion religion
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedPreferences preference to excludes
	 * @return whether religious preference exists
	 */
	boolean existsExcluding(Offender offender, Religion religion,
			Date startDate, Date endDate,
			ReligiousPreference... excludedPreferences);
	
	/**
	 * Count start date changes in period.
	 * 
	 * @param offender offender 
	 * @param startDate start date
	 * @param endDate end date
	 * @return number of start date changes in period
	 */
	long countStartDateChanges(Offender offender, Date startDate, Date endDate);

	/**
	 * Count start date changes in period excluding preferences.
	 * 
	 * @param offender offender 
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedPreferences preferences to exclude
	 * @return number of start date changes in period excluding preferences
	 */
	long countStartDateChangesExcluding(Offender offender, Date startDate,
			Date endDate, ReligiousPreference... excludedPreferences);

	/**
	 * Count end date changes in period.
	 * 
	 * @param offender offender 
	 * @param startDate start date
	 * @param endDate end date
	 * @return number of end date changes in period
	 */
	long countEndDateChanges(Offender offender, Date startDate, Date endDate);
	
	/**
	 * Count end date changes in period excluding preferences.
	 * 
	 * @param offender offender 
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedPreferences preferences to exclude
	 * @return number of end date changes in period excluding preferences
	 */
	long countEndDateChangesExcluding(Offender offender, Date startDate,
			Date endDate, ReligiousPreference... excludedPreferences);
}