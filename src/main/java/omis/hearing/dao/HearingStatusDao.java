package omis.hearing.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingStatus;
import omis.hearing.domain.HearingStatusCategory;

/**
 * HearingStatusDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface HearingStatusDao extends GenericDao<HearingStatus> {
	
	/**
	 * Returns a HearingStatus found with specified properties
	 * @param hearing - Hearing
	 * @param date - Date
	 * @param category - HearingStatusCategory
	 * @return HearingStatus with specified properties
	 */
	public HearingStatus find(Hearing hearing, Date date,
			HearingStatusCategory category);
	
	/**
	 * Returns a HearingStatus found with specified properties
	 * excluding specified HearingStatus
	 * @param hearing - Hearing
	 * @param date - Date
	 * @param category - HearingStatusCategory
	 * @param hearingStatusExcluded - HearingStatus to exclude from search
	 * @return HearingStatus with specified properties excluding specified
	 * HearingStatus
	 */
	public HearingStatus findExcluding(Hearing hearing, Date date,
			HearingStatusCategory category, HearingStatus hearingStatusExcluded);
	
	/**
	 * Returns the HearingStatus with the most recent Date for specified Hearing
	 * @param hearing - Hearing
	 * @return HearingStatus with the most recent Date for specified Hearing
	 */
	public HearingStatus findLatestByHearing(Hearing hearing);
	
	/**
	 * Returns a list of all HearingStatuses for specified Hearing
	 * @param hearing - Hearing
	 * @return List of all HearingStatuses for specified Hearing
	 */
	public List<HearingStatus> findByHearing(Hearing hearing);
}
