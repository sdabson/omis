package omis.health.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

/**
 * Data access object for external referrals.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralDao
		extends GenericDao<ExternalReferral> {

	/**
	 * Returns the external referral authorized by the authorization.
	 * 
	 * <p>Returns {@code null} if no external referral is authorized by the
	 * authorization.
	 * 
	 * @param authorization authorization
	 * @return external referral authorized by authorization
	 */
	ExternalReferral findByAuthorization(
			ExternalReferralAuthorization authorization);
	
	/**
	 * Returns the external referral by business key.
	 * @param offender offender
	 * @param date date	
	 * @param startTime startTime	
	 * @param providerAssignment provider assignment
	 * @return external referral
	 */
	// TODO: Remove providerAssignment as it is not part of business key - SA
	ExternalReferral find(Offender offender, Date date, Date startTime, 
			ProviderAssignment providerAssignment);

	/**
	 * Returns the external referral by specified properties until matching
	 * excluded referral.
	 * 
	 * @param excludedReferral referral to exclude
	 * @param offender offender
	 * @param date date
	 * @param startTime start time
	 * @param providerAssignment provider assignment
	 * @return external referral with specified properties
	 */
	// TODO: Remove providerAssignment as it is not part of business key - SA
	ExternalReferral findExcluding(ExternalReferral excludedReferral,
			Offender offender, Date date, Date startTime,
			ProviderAssignment providerAssignment);
}