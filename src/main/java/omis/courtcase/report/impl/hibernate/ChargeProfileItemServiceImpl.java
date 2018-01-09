package omis.courtcase.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.courtcase.report.ChargeProfileItemService;
import omis.offender.domain.Offender;

/** 
 * Implementation of charge profile item service.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 16, 2017)
 * @since OMIS 3.0 
 */
public class ChargeProfileItemServiceImpl 
	implements ChargeProfileItemService {
	private static final String 
		FIND_CHARGE_COUNT_BY_OFFENDER_QUERY_NAME 
			= "findPendingChargeCountByDefendant";
		
	private static final String OFFENDER_PARAM_NAME = "defendant";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public ChargeProfileItemServiceImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findChargeCountByOffender(
			final Offender offender) {
		Long count = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_CHARGE_COUNT_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return count.intValue();
	}
}
