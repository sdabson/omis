package omis.health.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

/**
 * Data access object for internal referrals.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 14, 2014)
 * @since OMIS 3.0
 */
public interface InternalReferralDao
		extends GenericDao<InternalReferral> {

	/** Returns internal referral by business key.
	 * @param offenderAppointmentAssociation offender appointment association.
	 * @return internal referral. */
	InternalReferral find(OffenderAppointmentAssociation
			offenderAppointmentAssociation);

	/** Return referral by business key.
	 * @param offender offender.
	 * @param date date.
	 * @param startTime start time.
	 * @param provider provider
	 * @return internal referral. */
	InternalReferral find(Offender offender, Date date, Date startTime,
			ProviderAssignment providerAssignment);

	/** Returns internal referral by business key excluding.
	 * @param offenderAppointmentAssociation offender appointment association
	 * excluding.
	 * @param exclude exclude.
	 * @return internal referral. */
	InternalReferral findExcluding(
			OffenderAppointmentAssociation offenderAppointmentAssociation,
			InternalReferral exclude);


	/** Return referral by business key excluding.
	 * @param offender offender.
	 * @param date date.
	 * @param startTime start time.
	 * @param provider provider
	 * @param exclude exclude.
	 * @return internal referral. */
	InternalReferral findExcluding(Offender offender, Date date, Date startTime,
			ProviderAssignment providerAssignment, InternalReferral exclude);

}