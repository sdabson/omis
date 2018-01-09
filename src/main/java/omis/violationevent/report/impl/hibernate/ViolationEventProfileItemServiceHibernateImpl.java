package omis.violationevent.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.violationevent.report.ViolationEventProfileItemService;

/**
 * Violation event profile item service hibernate implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 13, 2017)
 * @since OMIS 3.0
 */
public class ViolationEventProfileItemServiceHibernateImpl 
	implements ViolationEventProfileItemService {
	
	private static final String FIND_VIOLATION_EVENT_COUNT_BY_OFFENDER_QUERY_NAME
		= "findViolationEventCountByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;

	/** Instantiates an implementation of ViolationEventProfileItemServiceHibernateImpl */
	public ViolationEventProfileItemServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findViolationEventCountByOffender(final Offender offender) {
		Query query = this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_VIOLATION_EVENT_COUNT_BY_OFFENDER_QUERY_NAME);
			query.setEntity(OFFENDER_PARAM_NAME, offender);
		
		return ((Long) query.uniqueResult()).intValue();			
	}
}