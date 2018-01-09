package omis.stg.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupAffiliation;

/**
 * Data access object for security threat group affiliations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupAffiliationDao
		extends GenericDao<SecurityThreatGroupAffiliation> {

	/**
	 * Returns security threat group affiliations for offender.
	 * 
	 * @param offender offender
	 * @return security threat groups for offender
	 */
	List<SecurityThreatGroupAffiliation> findByOffender(Offender offender);
	
	/**
	 * Finds the security threat group affiliation.
	 * 
	 * @param offender offender
	 * @param group group
	 * @param startDate start date
	 * @param endDate end date
	 * @return security threat group affiliation
	 */
	SecurityThreatGroupAffiliation find(Offender offender,
			SecurityThreatGroup group, Date startDate, Date endDate);
	
	/**
	 * Finds the security threat group affiliation excluding the affiliation.
	 * 
	 * @param offender offender
	 * @param group group
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedAffiliation affiliation to exclude
	 * @return security threat group affiliation
	 */
	SecurityThreatGroupAffiliation findExcluding(Offender offender,
			SecurityThreatGroup group, Date startDate, Date endDate,
			SecurityThreatGroupAffiliation excludedAffiliation);
}