package omis.hearing.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.hearing.report.HearingProfileItemService;
import omis.hearing.report.HearingProfileItemSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of hearing profile item report.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 27, 2017)
 * @since OMIS 3.0
 */
public class HearingProfileItemServiceHibernateImpl 
		implements HearingProfileItemService {
	
	private final SessionFactory sessionFactory;
	
	private static final String 
		FIND_HEARING_PROFILE_ITEM_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findHearingProfileItemSummaryByOffender";

	private static final String OFFENDER_PARAM_NAME
		= "offender";
	
	/** Instantiates an implementation of 
	 * Hearing Profile Item Service 
	 * @param sessionFactory session factory
	 */
	public HearingProfileItemServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public HearingProfileItemSummary findHearingProfileItemSummaryByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_HEARING_PROFILE_ITEM_SUMMARY_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return (HearingProfileItemSummary) q.uniqueResult();				
	}
}