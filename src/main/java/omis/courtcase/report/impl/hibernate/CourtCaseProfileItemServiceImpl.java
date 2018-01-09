package omis.courtcase.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.courtcase.report.CourtCaseProfileItemService;
import omis.offender.domain.Offender;

/** Implementation of court case profile item service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0 */
public class CourtCaseProfileItemServiceImpl 
	implements CourtCaseProfileItemService {
	private static final String 
		FIND_COURT_CASE_COUNT_BY_OFFENDER_QUERY_NAME 
			= "findCourtCaseCountByDefendant";
		
	private static final String OFFENDER_PARAM_NAME = "defendant";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public CourtCaseProfileItemServiceImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Integer findCourtCaseCountByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(FIND_COURT_CASE_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return ((Long) q.uniqueResult()).intValue();
	}
}
